package com.sun.health.service.sms;

import com.sun.health.config.YunPianConfig;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class YunPianService extends AbstractService {

    @Autowired
    private YunPianConfig yunPianConfig;

    public void sendSingle(String mobile, String text) {

        HttpClient httpClient = HttpClient.newBuilder()
                .build();

        String body = "mobile=" + mobile + "&text=" + text + "&apikey=" + yunPianConfig.getApikey();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(YunPianConfig.SINGLE))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("[云片]发送短信，返回：{}", httpResponse.body());
        } catch (IOException e) {
            logger.error("[云片]发送短信.io exception", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("[云片]发送短信.interrupted exception", e);
        }

    }
}
