package com.sun.health.controller.bandao.cart;

import com.sun.health.controller.BaseController;
import com.sun.health.core.comm.JsonRet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {

    /**
     * 获取购物车列表
     */
    @GetMapping
    public JsonRet<String> getCarts(){

        return JsonRet.success();
    }
}
