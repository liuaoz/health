package com.sun.health.controller;

import com.sun.health.comm.Const;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.core.util.FileUtil;
import com.sun.health.core.util.JsonUtil;
import com.sun.health.service.hospital.zhangzhou.ZhangZhouReportParser;
import com.sun.health.service.tencent.TencentService;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/parser")
public class ParserController extends BaseController {

    @Autowired
    private ZhangZhouReportParser zhangZhouReportParser;

    @Autowired
    private TencentService tencentService;

    @GetMapping("/zhangzhou/{fileName}")
    public JsonRet<?> parseZhangZhouReport(@PathVariable String fileName) {
        String imageUrl = Const.imagePath + fileName;
        //1. 调用第三方ocr接口识别，返回json
        GeneralBasicOCRResponse response;
        try {
            response = tencentService.basicOcr(FileUtil.toByteArrayByNio(new File(imageUrl)));
        } catch (IOException e) {
            logger.error("call tencent ocr api error.", e);
            return JsonRet.fail();
        }
        //2. 解析json，存储到db之中
        String content = null;
        zhangZhouReportParser.parse(JsonUtil.toJson(response));
        return JsonRet.success();
    }
}
