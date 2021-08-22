package com.sun.health.controller;


import com.sun.health.entity.blood.BloodReportEntity;
import com.sun.health.service.BloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> blood(@RequestParam String fileName) {
        String imageUrl = "D://tmp//" + fileName;
        List<BloodReportEntity> list = bloodService.parse(imageUrl);
        list.forEach(t -> {

            System.out.println(String.format("%-40s", t.getItem().trim()) + String.format("%-40s", t.getResult().trim()) + String.format("%-40s", t.getReference().trim()));
        });
        return ResponseEntity.ok("success");
    }
}
