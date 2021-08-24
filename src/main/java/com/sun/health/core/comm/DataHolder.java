package com.sun.health.core.comm;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/24
 **/
public class DataHolder<Boolean, T> {

    private Boolean hasData;

    private T data;

    public DataHolder() {
    }

    public DataHolder(Boolean hasData, T data) {
        this.hasData = hasData;
        this.data = data;
    }

    public Boolean getHasData() {
        return hasData;
    }

    public void setHasData(Boolean hasData) {
        this.hasData = hasData;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
