package com.sun.health.controller.bandao.pay;

import com.sun.health.comm.WxPayNotice;
import com.sun.health.controller.BaseController;
import com.sun.health.core.util.JsonUtil;
import com.sun.health.dto.bandao.pay.wx.EncryptedRespDto;
import com.sun.health.service.bandao.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 支付成功，微信回调
     *
     * @param dto <xml>
     *            <appid><![CDATA[wx3155c0d75306acc8]]></appid>
     *            <bank_type><![CDATA[OTHERS]]></bank_type>
     *            <cash_fee><![CDATA[1]]></cash_fee>
     *            <fee_type><![CDATA[CNY]]></fee_type>
     *            <is_subscribe><![CDATA[N]]></is_subscribe>
     *            <mch_id><![CDATA[1550938701]]></mch_id>
     *            <nonce_str><![CDATA[20220110124809]]></nonce_str>
     *            <openid><![CDATA[owJBV43C3ugI76lqpuFpzWQt7o3c]]></openid>
     *            <out_trade_no><![CDATA[20220110124809]]></out_trade_no>
     *            <result_code><![CDATA[SUCCESS]]></result_code>
     *            <return_code><![CDATA[SUCCESS]]></return_code>
     *            <sign><![CDATA[F9C4D6A647987F824B70A53CD79782CD]]></sign>
     *            <time_end><![CDATA[20220110204818]]></time_end>
     *            <total_fee>1</total_fee>
     *            <trade_type><![CDATA[JSAPI]]></trade_type>
     *            <transaction_id><![CDATA[4200001299202201109649978575]]></transaction_id>
     *            </xml>
     *            ----------------------------------------------------------------------------
     * @return <xml>
     * <return_code><![CDATA[SUCCESS]]></return_code>
     * <return_msg><![CDATA[OK]]></return_msg>
     * </xml>
     */
    @PostMapping(value = "notify", consumes = "text/xml", produces = "text/xml")
    public String notify(@RequestBody EncryptedRespDto dto) {
        logger.warn("wx pay notify->{}", JsonUtil.toJson(dto));
        if (WxPayNotice.SUCCESS.name().equals(dto.getReturn_code())
                && WxPayNotice.SUCCESS.name().equals(dto.getResult_code())
                && checkSign()) {
            orderService.finishPay(dto.getOut_trade_no());
            return WxPayNotice.noticeSuccess();
        }
        return WxPayNotice.noticeFail();
    }

    private boolean checkSign() {
        //todo
        return true;
    }

}
