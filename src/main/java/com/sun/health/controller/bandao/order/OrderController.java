package com.sun.health.controller.bandao.order;

import com.sun.health.comm.Const;
import com.sun.health.controller.BaseController;
import com.sun.health.core.annotation.CurrentUser;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.dto.bandao.order.OrderMasterDto;
import com.sun.health.dto.bandao.pay.PrepayDto;
import com.sun.health.dto.bandao.pay.UnifiedOrderRespDto;
import com.sun.health.entity.bandao.user.UserEntity;
import com.sun.health.service.bandao.order.OrderService;
import org.springframework.beans.BeanUtils;
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
        List<OrderMasterDto> list = orderService.getOrderListWithGoods(user.getId());
        list.forEach(master -> master.getGoods().forEach(good -> good.setLogo(Const.imageServer + good.getLogo())));
        return JsonRet.success(list);
    }

    @PostMapping("/submit/{addressId}")
    public JsonRet<Boolean> submitOrder(@CurrentUser UserEntity user, @PathVariable Long addressId) {
        orderService.submitOrder(user.getId(), addressId);
        return JsonRet.success(Boolean.TRUE);
    }

    @PostMapping("/prepay/{orderId}")
    public JsonRet<PrepayDto> prepay(@PathVariable Long orderId) {
        UnifiedOrderRespDto respDto = orderService.prePay(orderId);
        PrepayDto prepayDto = new PrepayDto();
        BeanUtils.copyProperties(respDto, prepayDto);
        return JsonRet.success(prepayDto);
    }

    /**
     * 支付成功，微信回调
     */
    @PostMapping(Const.NOTIFY_URL)
    public JsonRet<Boolean> confirm() {
        logger.info("wx pay notify............");
        return JsonRet.success();
    }
}
