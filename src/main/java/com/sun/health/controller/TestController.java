package com.sun.health.controller;

import com.sun.health.core.util.FileUtil;
import com.sun.health.service.tencent.TencentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/21
 **/
@RestController
@RequestMapping("/test/")
public class TestController extends BaseController {

    @Autowired
    private TencentService tencentService;

    @GetMapping("tencent")
    public void test(@RequestParam String fileName) {
        String imageUrl = "D://tmp//" + fileName;
        try {
            tencentService.ocr(FileUtil.toByteArrayByNio(new File(imageUrl)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
