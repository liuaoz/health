package com.sun.health.controller.bandao.pay;

import com.sun.health.controller.BaseController;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.service.bandao.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController extends BaseController {

    @Autowired
    private WxPayService wxPayService;

    @GetMapping("/wx")
    public JsonRet<String> pay() {
        wxPayService.unifiedOrder(null);
//        wxPayService.getOpenid();
        return JsonRet.success();
    }
}
