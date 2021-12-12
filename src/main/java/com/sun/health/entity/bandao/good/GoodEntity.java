package com.sun.health.entity.bandao.good;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_goods")
public class GoodEntity extends BaseEntity {

    private String name;

    private String icon;



}
