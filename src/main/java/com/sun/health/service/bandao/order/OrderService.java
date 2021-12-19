package com.sun.health.service.bandao.order;

import com.sun.health.dto.bandao.order.OrderDto;
import com.sun.health.entity.bandao.order.OrderMasterEntity;
import com.sun.health.repository.bandao.order.OrderDetailRepository;
import com.sun.health.repository.bandao.order.OrderMasterRepository;
import com.sun.health.service.AbstractService;
import com.sun.health.service.bandao.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends AbstractService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private WxPayService wxPayService;

    /**
     * 用户订单列表
     */
    public List<OrderMasterEntity> getOrderList(Long userId) {
        return orderMasterRepository.findByUserId(userId);
    }


    /**
     * 去支付时，到wx生成预定单，返回prepare_id
     */
    public void prePay(Long  orderId){

        wxPayService.unifiedOrder();
    }

    /**
     * 添加订单
     */
    public void createOrder(OrderMasterEntity orderMasterEntity){

        OrderMasterEntity masterEntity = orderMasterRepository.save(orderMasterEntity);



    }
}
