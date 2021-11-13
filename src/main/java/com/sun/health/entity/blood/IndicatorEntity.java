package com.sun.health.entity.blood;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/22
 **/
@Entity
@Table(name = "tb_indicator")
public class IndicatorEntity extends BaseEntity {

    @Column(unique = true)
    private String item;

    private String abbr;

    private String reference;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
