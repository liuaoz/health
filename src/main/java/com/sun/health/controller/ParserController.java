package com.sun.health.controller;

import com.sun.health.core.comm.JsonRet;
import com.sun.health.service.hospital.zhangzhou.ZhangZhouReportParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parser")
public class ParserController extends BaseController {

    @Autowired
    private ZhangZhouReportParser zhangZhouReportParser;

    @GetMapping("/zhangzhou")
    public JsonRet<Boolean> parseZhangZhouReport() {
        zhangZhouReportParser.handle();
        return JsonRet.success();
    }
}
