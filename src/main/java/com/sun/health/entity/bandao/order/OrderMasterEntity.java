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
     * 用户(下单人)
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

    /**
     * 收获时间
     */
    private Date receiveTime;

    /**
     * 收件人名称
     */
    private String receiverName;

    /**
     * 收件人电话
     */
    private String phone;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 地址
     */
    private String address;



}
