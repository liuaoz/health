package com.sun.health.entity.bandao.order;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_order_master")
public class OrderMasterEntity extends BaseEntity {

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 总金额，单位为分
     */
    private int totalFee;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 物品数量
     */
    private int goodQuantity;

    /**
     * 用户
     */
    private String userCode;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date shippedTime;



}
