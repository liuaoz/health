package com.sun.health.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/21
 **/
@Entity
@Table(name = "tb_medical_report")
public class MedicalReportEntity extends BaseEntity {

    private String item;

    private String result;

    private String reference;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
