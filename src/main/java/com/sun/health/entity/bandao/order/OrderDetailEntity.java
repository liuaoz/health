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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}