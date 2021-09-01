package com.sun.health.entity.hospital;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/9/1
 **/
@Entity
@Table(name = "tb_department")
public class DepartmentEntity extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
