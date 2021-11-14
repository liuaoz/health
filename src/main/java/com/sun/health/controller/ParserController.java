package com.sun.health.controller;

import com.sun.health.comm.Const;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.service.hospital.zhangzhou.ZhangZhouReportParser;
import com.sun.health.service.tencent.TencentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parser")
public class ParserController extends BaseController {

    @Autowired
    private ZhangZhouReportParser zhangZhouReportParser;

    @Autowired
    private TencentService tencentService;

    @GetMapping("/zhangzhou/{fileName}")
    public JsonRet<?> parseZhangZhouReport(@PathVariable String fileName) {
        zhangZhouReportParser.parse(fileName, Const.content);
        return JsonRet.success();
    }
}
