package com.sun.health.controller.blood;


import com.sun.health.controller.BaseController;
import com.sun.health.service.BloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 调用第三方接口，解析血报告
 *
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
        bloodService.handle(date);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/start")
    public ResponseEntity<String> blood() {

        bloodService.startHandle();
        return ResponseEntity.ok("success");
    }
}
