package com.sun.health.entity.life.shelter;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_life_housing_estate")
public class TbLifeHousingEstateEntity extends BaseEntity {

    private String code;

    private String name;

    private String address;

    private String latitude;

    private String longitude;

    private String province;

    private String city;

    private String district;

    private String region;

    private String street;
}
