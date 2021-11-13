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
@RequestMapping("/tencent/")
public class TencentController extends BaseController {

    @Autowired
    private TencentService tencentService;

    public static final String imagePath = "/Users/stonechen/tmp/report_image/";

    @GetMapping("basicOcr")
    public void basicOcr(@RequestParam String fileName) {
        String imageUrl = imagePath + fileName;
        try {
            tencentService.basicOcr(FileUtil.toByteArrayByNio(new File(imageUrl)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("accurateOcr")
    public void accurateOcr(@RequestParam String fileName) {
        String imageUrl = imagePath + fileName;
        try {
            tencentService.accurateOcr(FileUtil.toByteArrayByNio(new File(imageUrl)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("advertiseOcr")
    public void advertiseOcr(@RequestParam String fileName) {
        String imageUrl = imagePath + fileName;
        try {
            tencentService.advertiseOcr(FileUtil.toByteArrayByNio(new File(imageUrl)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
