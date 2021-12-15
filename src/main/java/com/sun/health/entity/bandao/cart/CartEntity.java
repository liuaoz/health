package com.sun.health.entity.bandao.cart;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_cart")
public class CartEntity extends BaseEntity {

    /**
     * 客户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 商品数量
     */
    private int quantity;

    /**
     * 加入购物车时间
     */
    private Date addTime;
}
