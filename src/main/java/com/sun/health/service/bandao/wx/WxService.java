package com.sun.health.service.bandao.wx;

import com.sun.health.config.WxPayConfig;
import com.sun.health.core.util.JsonUtil;
import com.sun.health.dto.bandao.wx.SessionDto;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.MessageFormat;

@Service
public class WxService extends AbstractService {

    @Autowired
    private WxPayConfig wxPayConfig;


    public static final String code2session = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";

    @Nullable
    public SessionDto getUserSession(String code) {

        String url = MessageFormat.format(code2session, wxPayConfig.getAppid(), wxPayConfig.getAppSecret(), code);

        System.out.println(url);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();

            if (HttpStatus.valueOf(statusCode).is2xxSuccessful()) {
                logger.info("call wx api [code2Session] statusCode={}. body={}", statusCode, response.body());
                SessionDto dto = JsonUtil.fromJson(response.body(), SessionDto.class);
                if (WxErrorCode.isSuccessful(dto.getErrcode())) {
                    logger.info("call wx api [code2Session] success.");
                } else {
                    logger.error("call wx api [code2Session] failed.");
                }
                return dto;
            }

        } catch (IOException | InterruptedException e) {
            logger.error("call wx api [code2Session] failed.", e);
        }
        return null;
    }
}
