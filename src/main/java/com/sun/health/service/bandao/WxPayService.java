package com.sun.health.service.bandao;

import com.sun.health.config.WxPayConfig;
import com.sun.health.core.util.JsonUtil;
import com.sun.health.dto.bandao.pay.PayOrderDto;
import com.sun.health.service.AbstractService;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.ScheduledUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.UUID;

@Service
public class WxPayService extends AbstractService {

    @Autowired
    private WxPayConfig wxPayConfig;

    private static final String url = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";
    private static final String notify_url = "https://www.sunoribt.com";
    private static final String myOpenid = "owJBV43C3ugI76lqpuFpzWQt7o3c";

    private CloseableHttpClient httpClient;

    /**
     * 创建支付订单
     */
    public boolean createPayOrder() {
        //请求URL
        HttpPost httpPost = new HttpPost(url);

        // 请求body参数
        String body = JsonUtil.toJson(assemble());
        StringEntity entity = new StringEntity(body, "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        setup();
        //完成签名并执行请求
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.valueOf(statusCode).is2xxSuccessful()) {
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
            } else {
                System.out.println("failed,resp code = " + statusCode + ",return body = " + EntityUtils.toString(response.getEntity()));
                return false;
            }
        } catch (Exception e) {
            logger.error("create pay order error.", e);
            return false;
        }
        return true;
    }

    /**
     * 创建支付订单
     */
    public boolean createPayOrder2() {

        String body = JsonUtil.toJson(assemble());

        try {
            HttpClient client = HttpClient.newBuilder()
                    .sslContext(SSLContext.getDefault()) //TODO 签名
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .header("content-type", "application/json")
                    .header("Accept", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();

            if (HttpStatus.valueOf(statusCode).is2xxSuccessful()) {
                logger.info("create wx pay order success.");
            } else {
                logger.error("create wx pay order fail");
            }

        } catch (Exception e) {
            logger.error("create wx pay order error.", e);
        }
        return true;
    }

    private PayOrderDto assemble() {

        return new PayOrderDto().newBuilder()
                .buildAppid(wxPayConfig.getAppid())
                .buildAmount(1, "CNY")
                .buildDescription("this is desc")
                .buildMchid(wxPayConfig.getMchId())
                .buildPayer(myOpenid)
                .buildNotifyUrl(notify_url)
                .buildOutTradeNo(UUID.randomUUID().toString().substring(0, 32))
                .build();
    }

    public void getOpenid() {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.weixin.qq.com/sns/jscode2session?appid=" + wxPayConfig.getAppid()
                        + "&secret=" + wxPayConfig.getAppSecret() + "&js_code=041P2rFa1lTdhC0w2pIa1wSbA40P2rFf&grant_type=authorization_code"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("get openid response: {}", response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public SSLContext getSSLContext() {

        return null;
    }

    public void setup() {
        // 加载商户私钥（privateKey：私钥字符串）
        PrivateKey merchantPrivateKey = PemUtil
                .loadPrivateKey(new ByteArrayInputStream(wxPayConfig.getPrivateKey().getBytes(StandardCharsets.UTF_8)));

        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        ScheduledUpdateCertificatesVerifier verifier = new ScheduledUpdateCertificatesVerifier(
                new WechatPay2Credentials(wxPayConfig.getMchId(), new PrivateKeySigner(wxPayConfig.getMchSerialNo(), merchantPrivateKey)), wxPayConfig.getApiV3Key().getBytes(StandardCharsets.UTF_8));

        // 初始化httpClient
        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(wxPayConfig.getMchId(), wxPayConfig.getApiV3Key(), merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier)).build();
    }
}
