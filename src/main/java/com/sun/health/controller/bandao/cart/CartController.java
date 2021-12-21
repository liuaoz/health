package com.sun.health.controller.bandao.cart;

import com.sun.health.comm.Const;
import com.sun.health.controller.BaseController;
import com.sun.health.core.annotation.CurrentUser;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.dto.bandao.cart.CartDto;
import com.sun.health.dto.bandao.cart.CartGoodDto;
import com.sun.health.entity.bandao.user.UserEntity;
import com.sun.health.service.bandao.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;

    @PutMapping("/selectAll/{selectAll}")
    public JsonRet<Boolean> selectHandle(@CurrentUser UserEntity user, @PathVariable boolean selectAll) {
        cartService.handleSelectAll(selectAll, user.getId());
        return JsonRet.success(Boolean.TRUE);
    }

    /**
     * 添加到购物车
     */
    @PostMapping
    public JsonRet<Boolean> addToCart(@RequestBody CartDto dto, @CurrentUser UserEntity user) {

        dto.setUserId(user.getId());
        cartService.save(dto);
        return JsonRet.success(Boolean.TRUE);
    }

    /**
     * 更新
     */
    @PutMapping
    public JsonRet<Boolean> updateCart(@RequestBody CartDto dto, @CurrentUser UserEntity user) {
        dto.setUserId(user.getId());
        cartService.save(dto);
        return JsonRet.success(Boolean.TRUE);
    }


    /**
     * 获取购物车列表
     */
    @GetMapping("/list")
    public JsonRet<List<CartGoodDto>> getCartList(@CurrentUser UserEntity user) {
        List<CartGoodDto> cartList = cartService.getCartList(user.getId());
        cartList.forEach(t -> t.getGood().setLogo(Const.imageServer + t.getGood().getLogo()));
        return JsonRet.success(cartList);
    }
}