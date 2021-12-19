package com.sun.health.core.comm;

import java.io.Serializable;

/**
 * 框架统一响应结构
 *
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/30
 **/
public class JsonRet<T> implements Serializable {

    /**
     * @see com.sun.health.core.comm.CodeEnum
     */
    private int code;

    private String msg;

    private T data;

    public JsonRet() {
    }

    public JsonRet(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public JsonRet(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <E> JsonRet<E> success() {
        return new JsonRet<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), null);
    }

    public static <E> JsonRet<E> success(E data) {
        return new JsonRet<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), data);
    }

    public static JsonRet<String> fail() {
        return new JsonRet<>(CodeEnum.FAILED.getCode(), CodeEnum.FAILED.getMsg(), null);
    }

    public static JsonRet<String> fail(String msg) {
        return new JsonRet<>(CodeEnum.FAILED.getCode(), msg, null);
    }

    public static <E> JsonRet<E> ret(CodeEnum codeEnum, E data) {
        return new JsonRet<>(codeEnum.getCode(), codeEnum.getMsg(), data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
