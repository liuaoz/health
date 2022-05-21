package com.sun.health.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("sms.yunpian")
public class YunPianConfig {

    private String apikey;


    //yunpian
    public static final String SINGLE = "https://sms.yunpian.com/v2/sms/single_send.json";

    public static final String TEMPLATE = "【日道科技】内部通知，订单编号：#orderNo#,物流信息: #receiveInfo#";

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
}
