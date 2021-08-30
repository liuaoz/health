package com.sun.health.core.comm;

import java.io.Serializable;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/30
 **/
public class JsonRet<T> implements Serializable {

    private String code;

    private String msg;

    private T data;

    public JsonRet() {
    }

    public JsonRet(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public JsonRet(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
