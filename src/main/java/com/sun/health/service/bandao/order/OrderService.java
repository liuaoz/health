package com.sun.health.service.bandao.order;

import com.sun.health.comm.Const;
import com.sun.health.comm.OrderStatus;
import com.sun.health.core.util.DateUtil;
import com.sun.health.core.util.StringUtil;
import com.sun.health.dto.bandao.order.OrderDetailDto;
import com.sun.health.dto.bandao.order.OrderMasterDto;
import com.sun.health.dto.bandao.order.PrePayDto;
import com.sun.health.dto.bandao.pay.UnifiedOrderRespDto;
import com.sun.health.entity.bandao.address.UserAddressEntity;
import com.sun.health.entity.bandao.cart.CartEntity;
import com.sun.health.entity.bandao.good.GoodEntity;
import com.sun.health.entity.bandao.order.OrderDetailEntity;
import com.sun.health.entity.bandao.order.OrderMasterEntity;
import com.sun.health.repository.bandao.order.OrderDetailRepository;
import com.sun.health.repository.bandao.order.OrderMasterRepository;
import com.sun.health.service.AbstractService;
import com.sun.health.service.bandao.WxPayService;
import com.sun.health.service.bandao.address.UserAddressService;
import com.sun.health.service.bandao.cart.CartService;
import com.sun.health.service.bandao.good.GoodService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService extends AbstractService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private GoodService goodService;

    /**
     *
     */
    public OrderMasterEntity findById(Long id) {
        return orderMasterRepository.getById(id);
    }


    /**
     * 订单列表，包含商品信息
     */
    public List<OrderMasterDto> getOrderListWithGoods(Long userId) {

        List<OrderMasterEntity> orderMasterEntities = orderMasterRepository.findByUserId(userId);

        List<OrderMasterDto> orderMasterDtos = new ArrayList<>();

        orderMasterEntities.forEach(master -> {

            OrderMasterDto orderMasterDto = new OrderMasterDto();
            List<OrderDetailEntity> orderDetailList = getOrderDetailList(master.getId());

            BeanUtils.copyProperties(master, orderMasterDto);

            List<OrderDetailDto> orderDetailDtos = orderDetailList.stream().map(t -> {
                OrderDetailDto orderDetailDto = new OrderDetailDto();
                BeanUtils.copyProperties(t, orderDetailDto);
                return orderDetailDto;
            }).collect(Collectors.toList());

            orderMasterDto.setGoods(orderDetailDtos);
            orderMasterDtos.add(orderMasterDto);
        });

        return orderMasterDtos;
    }

    /**
     * 用户订单列表
     */
    public List<OrderMasterEntity> getOrderList(Long userId) {
        return orderMasterRepository.findByUserId(userId);
    }

    public List<OrderDetailEntity> getOrderDetailList(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    /**
     * 去支付时，到wx生成预定单，返回prepare_id
     */
    @Nullable
    public UnifiedOrderRespDto prePay(Long orderId) {
        OrderMasterEntity masterEntity = findById(orderId);
        return wxPayService.unifiedOrder(masterEntity);
    }

    /**
     * 生成订单
     */
    public void submitOrder(Long userId, Long addressId) {

        List<CartEntity> selectedCart = cartService.getSelectedCart(userId);

        if (selectedCart.isEmpty()) {
            logger.warn("请选择要下单的商品");
            return;
        }

        UserAddressEntity userAddress = userAddressService.findById(addressId);

        List<Long> goodIds = selectedCart.stream().map(CartEntity::getGoodId).collect(Collectors.toList());

        List<GoodEntity> goodEntities = goodService.getByIds(goodIds);

        Map<Long, GoodEntity> idGoodMap = goodEntities.stream().collect(Collectors.toMap(GoodEntity::getId, t -> t));

        //order basic
        OrderMasterEntity orderMaster = new OrderMasterEntity();
        orderMaster.setUserId(userId);
        orderMaster.setOrderNo(DateUtil.getStringTime(new Date()));
        orderMaster.setStatus(OrderStatus.TO_BE_PAID.getCode());
        int totalFee = selectedCart.stream().mapToInt(t -> {
            GoodEntity goodEntity = idGoodMap.get(t.getGoodId());
            return goodEntity.getPrice() * t.getQuantity();
        }).sum();
        int goodQuantity = selectedCart.size();//todo
        orderMaster.setTotalFee(totalFee);
        orderMaster.setGoodQuantity(goodQuantity);

        //order address
        orderMaster.setRecipient(userAddress.getRecipient());
        orderMaster.setPhone(userAddress.getPhone());
        orderMaster.setProvince(userAddress.getProvince());
        orderMaster.setCity(userAddress.getCity());
        orderMaster.setDistrict(userAddress.getDistrict());
        orderMaster.setAddress(userAddress.getAddress());

        //order detail
        orderMasterRepository.save(orderMaster);
        logger.info("生成订单.订单id={}", orderMaster.getId());

        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
        selectedCart.forEach(cart -> {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setOrderId(orderMaster.getId());
            orderDetailEntity.setGoodId(cart.getGoodId());
            orderDetailEntity.setQuantity(1);//todo
            GoodEntity goodEntity = idGoodMap.get(cart.getGoodId());
            orderDetailEntity.setGoodName(goodEntity.getName());
            orderDetailEntity.setPrice(goodEntity.getPrice());
            orderDetailEntity.setLogo(goodEntity.getLogo());
            orderDetailEntities.add(orderDetailEntity);
        });
        orderDetailRepository.saveAll(orderDetailEntities);

        // 移除购物车
        cartService.removeSelectedCart(userId);
    }
}
