package com.sun.health.controller.bandao.order;

import com.sun.health.controller.BaseController;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.dto.bandao.order.OrderMasterDto;
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
    public JsonRet<List<OrderMasterDto>> getOrderList() {
        Long userId = 1L;
        orderService.getOrderList(userId);
        return JsonRet.success();
    }

    @PostMapping
    public JsonRet<String> createOrder(@RequestBody OrderMasterDto dto) {

        return JsonRet.success();
    }

    @PostMapping("/prepay/{orderId}")
    public JsonRet<String> prepay(@PathVariable Long orderId) {
        orderService.prePay(orderId);
        return JsonRet.success("todo");
    }
}
