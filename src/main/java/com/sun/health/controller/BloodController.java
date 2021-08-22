package com.sun.health.controller;


import com.sun.health.entity.blood.BloodReportEntity;
import com.sun.health.service.BloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/22
 **/
@RestController
@RequestMapping("/blood")
public class BloodController extends BaseController {


    @Autowired
    private BloodService bloodService;

    @GetMapping("/tencent")
    public ResponseEntity<String> blood(@RequestParam String date) {
//        String imageUrl = "D://tmp//" + fileName;
        String parentDir = "/Users/stonechen/father/" + date + "/";
        File file = new File(parentDir);
        File[] files = file.listFiles();

        if (files == null) {
            return ResponseEntity.ok("error");
        }
        bloodService.delete(date);

        Arrays.stream(files).forEach(k -> {
            String imageName = k.getName();
            String imageUrl = parentDir + imageName;
            List<BloodReportEntity> list = bloodService.parse(imageUrl);
            list.forEach(t -> System.out.println(String.format("%-40s", t.getItem().trim()) + String.format("%-40s", t.getResult().trim()) + String.format("%-40s", t.getReference().trim())));
        });
        return ResponseEntity.ok("success");
    }
}
