package com.sun.health.entity.bandao.user;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user")
public class UserEntity extends BaseEntity {

    private String openId;

    private String unionId;

    private String nickName;

    private String userName;
}
