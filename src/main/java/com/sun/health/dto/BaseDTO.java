package com.sun.health.dto;

import java.io.Serializable;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/22
 **/
public abstract class BaseDTO implements Serializable {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
