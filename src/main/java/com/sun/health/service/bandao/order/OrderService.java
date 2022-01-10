package com.sun.health.service.bandao.order;

import com.sun.health.comm.Const;
import com.sun.health.comm.OrderStatus;
import com.sun.health.config.WxPayConfig;
import com.sun.health.core.util.DateUtil;
import com.sun.health.core.util.SafeUtil;
import com.sun.health.core.util.StringUtil;
import com.sun.health.dto.bandao.order.OrderDetailDto;
import com.sun.health.dto.bandao.order.OrderMasterDto;
import com.sun.health.dto.bandao.order.PrePayDto;
import com.sun.health.dto.bandao.pay.PayOrderDto;
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
    @Autowired
    private WxPayConfig wxPayConfig;

    /**
     * 获取订单
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

    /**
     * 获取订单明细
     *
     * @param orderId 订单id
     * @return 订单明细
     */
    public List<OrderDetailEntity> getOrderDetailList(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    /**
     * 取消订单
     */
    public boolean cancelOrder(Long id) {
        OrderMasterEntity order = findById(id);
        if (Objects.nonNull(order) && OrderStatus.TO_BE_PAID.name().equals(order.getStatus())) {
            order.setStatus(OrderStatus.CANCELLED.getCode());
            orderMasterRepository.save(order);
            return true;
        }
        return false;
    }

    /**
     * 完成支付
     */
    public void finishPay(String orderNo) {

        OrderMasterEntity order = orderMasterRepository.findByOrderNo(orderNo);

        if (Objects.isNull(order)) {
            logger.warn("订单不存在,orderNo={}", orderNo);
            return;
        }

        if (OrderStatus.hasPaid(order.getStatus())) {
            logger.warn("订单已支付,orderNo={}", orderNo);
            return;
        }

        if (OrderStatus.TO_BE_PAID.getCode().equals(order.getStatus())) {
            //修改订单状态为已完成
            order.setStatus(OrderStatus.PAID.getCode());
            orderMasterRepository.save(order);
        } else {
            logger.warn("订单号：{},订单状态为{}。支付通知不做任何处理。", orderNo, order.getStatus());
        }
    }

    /**
     * 去支付时，到wx生成预定单，返回prepare_id
     */
    @Nullable
    public UnifiedOrderRespDto prePay(Long orderId) {
        OrderMasterEntity masterEntity = findById(orderId);
        UnifiedOrderRespDto respDto = wxPayService.unifiedOrder(masterEntity);

        Date now = new Date();
        respDto.setTime_stamp(String.valueOf(now.getTime() / 1000));
        respDto.setSign_type("MD5");

        String source = "appId=" + wxPayConfig.getAppid()
                + "&nonceStr=" + respDto.getNonce_str()
                + "&package=prepay_id=" + respDto.getPrepay_id()
                + "&signType=" + respDto.getSign_type()
                + "&timeStamp=" + respDto.getTime_stamp()
                + "&key=" + wxPayConfig.getKey();

        logger.info("prepay request-->" + source);
        String paySign = SafeUtil.md5(source).toUpperCase();
        respDto.setPay_sign(paySign);
        return respDto;
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
