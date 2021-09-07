package com.sun.health.core.comm;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/9/7
 **/
public enum CodeEnum {

    SUCCESS(0, "success"),
    FAILED(-1, "failure"),

    UNKNOWN(-9999, "unknown error.");

    private int code;
    private String msg;

    CodeEnum() {
    }

    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
