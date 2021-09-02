package com.sun.health.entity.crawler;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 2471种疾病
 */
@Entity
@Table(name = "tb_dai_fu")
public class DaiFuEntity extends BaseEntity {

    private String name;

    private String url;

    private boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
