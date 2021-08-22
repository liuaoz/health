package com.sun.health.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/21
 **/
@Configuration
@ConfigurationProperties(prefix = "cloud.tencent")
public class TencentConfig {

    private String secretId;

    private String secretKey;

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
