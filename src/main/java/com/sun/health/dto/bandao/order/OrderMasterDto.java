package com.sun.health.dto.bandao.order;

import com.sun.health.dto.BaseDTO;

import java.util.Date;
import java.util.List;

public class OrderMasterDto extends BaseDTO {

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
    private Long userId;

    /**
     * 下单时间
     */
    private Date orderTime;


    private List<OrderDetailDto> goods;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getGoodQuantity() {
        return goodQuantity;
    }

    public void setGoodQuantity(int goodQuantity) {
        this.goodQuantity = goodQuantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderDetailDto> getGoods() {
        return goods;
    }

    public void setGoods(List<OrderDetailDto> goods) {
        this.goods = goods;
    }
}
