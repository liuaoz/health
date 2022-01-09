package com.sun.health.entity.bandao.pay;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pay_log")
public class PayLogEntity extends BaseEntity {

    private Long userId;


}
