package com.sun.health.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/21
 **/
@Configuration
@ConfigurationProperties(prefix = "cloud.kdxf")
public class KdxfConfig {

    private String appId;

    private String appKey;

    private String ocrUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getOcrUrl() {
        return ocrUrl;
    }

    public void setOcrUrl(String ocrUrl) {
        this.ocrUrl = ocrUrl;
    }
}
