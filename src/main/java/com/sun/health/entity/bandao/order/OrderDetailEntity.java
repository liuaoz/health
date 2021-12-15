package com.sun.health.entity.bandao.order;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_order_detail")
public class OrderDetailEntity extends BaseEntity {

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 购买数量
     */
    private int quantity;

    /**
     * 商品单价
     */
    private int price;


}
