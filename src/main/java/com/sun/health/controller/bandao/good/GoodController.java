package com.sun.health.controller.bandao.good;

import com.sun.health.controller.BaseController;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.dto.bandao.good.GoodDto;
import com.sun.health.entity.bandao.good.GoodEntity;
import com.sun.health.service.bandao.good.GoodService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/good")
public class GoodController extends BaseController {

    public static final String imageServer = "http://127.0.0.1:8080/health/image/";

    @Autowired
    private GoodService goodService;

    @GetMapping("/list")
    public JsonRet<List<GoodDto>> getGoods() {
        List<GoodEntity> goods = goodService.getGoods();
        List<GoodDto> list = goods.stream().map(t -> {
            GoodDto dto = new GoodDto();
            BeanUtils.copyProperties(t, dto);
            return dto;
        }).collect(Collectors.toList());

        list.forEach(t->t.setLogo(imageServer+t.getLogo()));

        return JsonRet.success(list);
    }
}
