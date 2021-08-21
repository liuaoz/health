package com.sun.health.controller;

import com.sun.health.service.baidu.BaiduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/21
 **/
@RestController
@RequestMapping("/test/")
public class TestController extends BaseController {

    @Autowired
    private BaiduService baiduService;

    @GetMapping("baidu")
    public void test(@RequestParam String fileName) {
        String imageUrl = "D://tmp//" + fileName;
        baiduService.ocr(imageUrl);
    }
}
