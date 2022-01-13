package com.sun.health.controller.bandao.good;

import com.sun.health.comm.Const;
import com.sun.health.controller.BaseController;
import com.sun.health.core.annotation.CurrentUser;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.core.util.FileUtil;
import com.sun.health.dto.bandao.good.GoodDto;
import com.sun.health.entity.bandao.good.GoodEntity;
import com.sun.health.entity.bandao.good.GoodImageEntity;
import com.sun.health.entity.bandao.user.UserEntity;
import com.sun.health.service.bandao.cart.CartService;
import com.sun.health.service.bandao.good.GoodService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/good")
public class GoodController extends BaseController {

    @Autowired
    private GoodService goodService;

    @Autowired
    private CartService cartService;

    @GetMapping("/{goodId}")
    public JsonRet<GoodDto> getGoodInfo(@PathVariable Long goodId) {
        GoodEntity entity = goodService.getById(goodId);
        GoodDto dto = new GoodDto();
        BeanUtils.copyProperties(entity, dto);
        return JsonRet.success(dto);
    }

    @GetMapping("/list")
    public JsonRet<List<GoodDto>> getGoods() {
        List<GoodEntity> goods = goodService.getGoods();
        List<GoodDto> list = goods.stream().map(t -> {
            GoodDto dto = new GoodDto();
            BeanUtils.copyProperties(t, dto);
            return dto;
        }).collect(Collectors.toList());

        list.forEach(t -> t.setLogo(Const.imageServer + t.getLogo()));

        return JsonRet.success(list);
    }

    @GetMapping("/good/image/{goodImageId}")
    public void getGoodImage(@PathVariable Long goodImageId){
        GoodImageEntity goodImageEntity = goodService.findById(goodImageId);


    }


    // 管理后台接口
    @PostMapping("/image/upload")
    public JsonRet<Boolean> uploadGoodImage(@RequestParam("files") MultipartFile[] files, @RequestParam("goodId") Long goodId) {
        boolean result = true;
        for (MultipartFile file : files) {
            boolean b = goodService.uploadGoodImage(goodId, file);
            if (!b) {
                result = false;
            }
        }
        return JsonRet.success(result);
    }
}
