package com.sun.health.controller.bandao.order;

import com.sun.health.controller.BaseController;
import com.sun.health.core.annotation.CurrentUser;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.dto.bandao.order.OrderMasterDto;
import com.sun.health.entity.bandao.user.UserEntity;
import com.sun.health.service.bandao.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public JsonRet<List<OrderMasterDto>> getOrderList(@CurrentUser UserEntity user) {
        Long userId = 1L;
        orderService.getOrderList(userId);
        return JsonRet.success();
    }

    @PostMapping("/submit/{addressId}")
    public JsonRet<String> submitOrder(@CurrentUser UserEntity user, @PathVariable Long addressId) {
        orderService.submitOrder(user.getId(), addressId);
        return JsonRet.success();
    }

    @PostMapping("/prepay/{orderId}")
    public JsonRet<String> prepay(@PathVariable Long orderId) {
        orderService.prePay(orderId);
        return JsonRet.success("todo");
    }
}
