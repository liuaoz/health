package com.sun.health.service.kdxf;

public class KdxfConfig {

    // OCR webapi 接口地址
    public static final String WEBOCR_URL = "https://webapi.xfyun.cn/v1/service/v1/ocr/general";
    // 应用ID (必须为webapi类型应用，并印刷文字识别服务，参考帖子如何创建一个webapi应用：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=36481)
    public static final String APPID = "*****";
    // 接口密钥(webapi类型应用开通印刷文字识别服务后，控制台--我的应用---印刷文字识别---服务的apikey)
    public static final String API_KEY = "*****";
    // 是否返回位置信息
    public static final String LOCATION = "false";
    // 语种(可选值：en（英文），cn|en（中文或中英混合)
    public static final String LANGUAGE = "cn|en";
    // 图片地址,图片最短边至少15px，最长边最大4096px，格式jpg、png、bmp
    public static final String PIC_PATH = "E://ti.jpg";
}
