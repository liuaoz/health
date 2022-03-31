package com.sun.health.entity.life.shelter;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_life_housing_estate_detail")
public class TbLifeHousingEstateDetailEntity extends BaseEntity {

    /**
     * 物业类型
     */
    private String propertyMgrType;

    private String ownershipType;

    private String buildDate;

    private String propertyRightYears;

    private String totalHouseHolds;

    private String totalArea;

    private String plotRatio;

    private String greenRate;

    private String buildingType;

    private String businessDistrict;

    //物业

    private String propertyFee;

    private String propertyCompany;

    private String developer;

}
