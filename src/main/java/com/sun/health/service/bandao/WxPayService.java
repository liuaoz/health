package com.sun.health.service.bandao;

import com.sun.health.comm.Const;
import com.sun.health.config.WxPayConfig;
import com.sun.health.core.util.SafeUtil;
import com.sun.health.core.util.StringUtil;
import com.sun.health.core.util.XmlUtil;
import com.sun.health.dto.bandao.pay.PayOrderDto;
import com.sun.health.dto.bandao.pay.UnifiedOrderRespDto;
import com.sun.health.entity.bandao.order.OrderMasterEntity;
import com.sun.health.entity.bandao.user.UserEntity;
import com.sun.health.service.AbstractService;
import com.sun.health.service.bandao.user.UserService;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.ScheduledUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.impl.client.CloseableHttpClient;
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

/**
 * 1. 小程序中的统一下单和jsapi有什么区别？
 */
@Service
public class WxPayService extends AbstractService {

    @Autowired
    private WxPayConfig wxPayConfig;

    @Autowired
    private UserService userService;

    private static final String url = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";
    public static final String url_unified_order = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//    private static final String notify_url = "https://www.sunoribt.com/health/pay/" + Const.NOTIFY_URL;
    public static final String spbill_create_ip = "106.14.67.97";

    private CloseableHttpClient httpClient;


    /**
     * 统一下单
     */
    public UnifiedOrderRespDto unifiedOrder(OrderMasterEntity masterEntity) {

        UnifiedOrderRespDto respDto;

        UserEntity user = userService.getById(masterEntity.getUserId());
        String body = assembleBody(masterEntity, user);

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url_unified_order))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("content-type", "text/xml")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (HttpStatus.valueOf(statusCode).is2xxSuccessful()) {
                logger.info("unified order response: {}", response.body());
                respDto = XmlUtil.convertXmlStrToObject(UnifiedOrderRespDto.class, response.body());
            } else {
                logger.error("unified order response code:" + statusCode);
                return null;
            }
        } catch (IOException | InterruptedException e) {
            logger.error("unified order error.", e);
            return null;
        }
        return respDto;
    }

    private String assembleBody(OrderMasterEntity masterEntity, UserEntity user) {

        String goodDesc = "海鲜";
        int totalFee = masterEntity.getTotalFee();
        String tradeType = "JSAPI";
        String nonceStr = masterEntity.getOrderNo();
        String outTradeNo = masterEntity.getOrderNo();

        String temp = String.join("&",
                "appid=" + wxPayConfig.getAppid(),
                "body=" + goodDesc,
                "mch_id=" + wxPayConfig.getMchId(),
                "nonce_str=" + nonceStr,
                "notify_url=" + wxPayConfig.getNotifyUrl(),
                "openid=" + user.getOpenId(),
                "out_trade_no=" + outTradeNo,
                "spbill_create_ip=" + spbill_create_ip,
                "total_fee=" + totalFee,
                "trade_type=" + tradeType,
                "key=" + wxPayConfig.getKey()
        );


        String sign = SafeUtil.md5(temp).toUpperCase();

        String body = "<xml>"
                + "<appid>" + wxPayConfig.getAppid() + "</appid>"
                + "<body>" + goodDesc + "</body>"
                + "<mch_id>" + wxPayConfig.getMchId() + "</mch_id>"
                + "<nonce_str>" + nonceStr + "</nonce_str>"
                + "<notify_url>" + wxPayConfig.getNotifyUrl() + "</notify_url>"
                + "<openid>" + user.getOpenId() + "</openid>"
                + "<out_trade_no>" + outTradeNo + "</out_trade_no>"
                + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                + "<total_fee>" + totalFee + "</total_fee>"
                + "<trade_type>" + tradeType + "</trade_type>"
                + "<sign>" + sign + "</sign>"
                + "</xml>";

        return body;
    }

    public void getOpenid() {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.weixin.qq.com/sns/jscode2session?appid=" + wxPayConfig.getAppid()
                        + "&secret=" + wxPayConfig.getAppSecret() + "&js_code=0815ys200C07ZM1dRb300SGt0w35ys2F&grant_type=authorization_code"))
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
