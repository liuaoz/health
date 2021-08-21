package com.sun.health.service.kdxf;

import com.sun.health.config.KdxfConfig;
import com.sun.health.core.util.FileUtil;
import com.sun.health.core.util.HttpUtil;
import com.sun.health.service.AbstractService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class KdxfService extends AbstractService {

    @Autowired
    private KdxfConfig kdxfConfig;

    // 是否返回位置信息
    public static final String LOCATION = "false";
    // 语种(可选值：en（英文），cn|en（中文或中英混合)
    public static final String LANGUAGE = "cn|en";
    // 图片地址,图片最短边至少15px，最长边最大4096px，格式jpg、png、bmp
    public static final String PIC_PATH = "D://tmp//test3.jpg";

    public void general() throws IOException {
        Map<String, String> header = buildHttpHeader();
        byte[] imageByteArray = FileUtil.read(PIC_PATH);
        String imageBase64 = new String(Base64.encodeBase64(imageByteArray), StandardCharsets.UTF_8);
        String result = null;
        try {
            result = HttpUtil.doPost1(kdxfConfig.getOcrUrl(), header, "image=" + URLEncoder.encode(imageBase64, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("OCR WebAPI 接口调用结果：" + result);
        //  错误码链接：https://www.xfyun.cn/document/error-code (code返回错误码时必看
    }

    /**
     * 组装http请求头
     */
    private Map<String, String> buildHttpHeader() {
        String curTime = System.currentTimeMillis() / 1000L + "";
        String param = "{\"location\":\"" + LOCATION + "\",\"language\":\"" + LANGUAGE + "\"}";
        String paramBase64 = new String(Base64.encodeBase64(param.getBytes(StandardCharsets.UTF_8)));
        String checkSum = DigestUtils.md5DigestAsHex((kdxfConfig.getAppKey() + curTime + paramBase64).getBytes());
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        header.put("X-Param", paramBase64);
        header.put("X-CurTime", curTime);
        header.put("X-CheckSum", checkSum);
        header.put("X-Appid", kdxfConfig.getAppId());
        return header;
    }
}
