package com.sun.health.controller.bandao.cart;

import com.sun.health.controller.BaseController;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.dto.bandao.cart.CartDto;
import com.sun.health.service.bandao.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;

    /**
     * 添加到购物车
     */
    @PostMapping
    public JsonRet<Boolean> addToCart(@RequestBody CartDto dto) {

        cartService.save(dto);

        return JsonRet.success(Boolean.TRUE);
    }


    /**
     * 获取购物车列表
     */
    @GetMapping
    public JsonRet<String> getCarts() {

        return JsonRet.success();
    }
}
