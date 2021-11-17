package com.sun.health.entity.blood;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_blood_item")
public class BloodItemEntity extends BaseEntity {

    /**
     * 项目名称
     */
    private String name;

    /**
     * 参考范围
     */
    private String reference;

    /**
     * 单位
     */
    private String unit;

    /**
     * 检验目的
     */
    private String inspectionPurpose;

    /**
     * 检验方法
     */
    private String inspectionMethod;

    /**
     * 标本种类
     */
    private String specimenType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getInspectionPurpose() {
        return inspectionPurpose;
    }

    public void setInspectionPurpose(String inspectionPurpose) {
        this.inspectionPurpose = inspectionPurpose;
    }

    public String getInspectionMethod() {
        return inspectionMethod;
    }

    public void setInspectionMethod(String inspectionMethod) {
        this.inspectionMethod = inspectionMethod;
    }

    public String getSpecimenType() {
        return specimenType;
    }

    public void setSpecimenType(String specimenType) {
        this.specimenType = specimenType;
    }
}
