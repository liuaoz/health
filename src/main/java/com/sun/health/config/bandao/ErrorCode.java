package com.sun.health.config.bandao;

public enum ErrorCode {

    INVALID_TOKEN(1000, "invalid token"),

    ;

    private int errCode;
    private String errMsg;

    ErrorCode(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
