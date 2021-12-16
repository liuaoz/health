package com.sun.health.service.bandao.wx;

/**
 * 微信响应错误码
 */
public enum WxErrorCode {

    INNER_ERR(-1, "系统繁忙，此时请开发者稍候再试"),

    SUCCESS(0, "请求成功"),

    INVALID_APP_ID(40013, "invalid appid"),

    INVALID_CODE(40029, "code无效"),

    FREQUENCY_LIMIT(45011, "频率限制，每个用户每分钟100次"),

    HIGH_RISK_USER(40226, "高风险等级用户，小程序登录拦截 ");

    private final int errcode;
    private final String errmsg;

    WxErrorCode(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public static boolean isSuccessful(int errcode) {
        return SUCCESS.getErrcode() == errcode;
    }

    public int getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }
}
