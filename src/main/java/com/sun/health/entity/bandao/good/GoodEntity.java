package com.sun.health.entity.bandao.good;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_goods")
public class GoodEntity extends BaseEntity {

    /**
     * 编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 在售状态： true：上架, false：下架
     */
    private boolean saleable;

    /**
     * 库存数量
     */
    private int stock;

    /**
     * 简介
     */
    private String outline;

    /**
     * 描述
     */
    private String intro;

    /**
     * 价格（单位：分）
     */
    private int price;




}
