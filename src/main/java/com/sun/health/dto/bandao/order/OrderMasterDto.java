package com.sun.health.dto.bandao.order;

import com.sun.health.dto.BaseDTO;

import java.util.Date;

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


}
