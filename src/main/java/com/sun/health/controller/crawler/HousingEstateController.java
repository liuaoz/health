package com.sun.health.controller.crawler;

import com.sun.health.comm.Const;
import com.sun.health.controller.BaseController;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.core.util.StringUtil;
import com.sun.health.entity.crawler.shelter.HousingEstateEntity;
import com.sun.health.service.crawler.house.HousingEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/housingEstate")
public class HousingEstateController extends BaseController {

    @Autowired
    private HousingEstateService housingEstateService;

    @GetMapping("/{keyword}")
    public JsonRet<List<HousingEstateEntity>> getData(@PathVariable String keyword) {
        List<HousingEstateEntity> list = housingEstateService.handle(Const.XIAO_QU_SHUO_BASE_URL + Const.XIAO_QU_SHUO_API_PREFIX + "?kw=" + keyword, keyword);
        return JsonRet.success(list);
    }

    @GetMapping("/start")
    public JsonRet<Boolean> start() {
        Date begin = new Date();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(new Random().nextInt(3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                String keyword = StringUtil.randLetter(3);
                String first = StringUtil.randHanzi();
//                String second = StringUtil.randHanzi();
                if (Objects.isNull(first) ){
                    continue;
                }
                String keyword = first ;
                String url = Const.XIAO_QU_SHUO_BASE_URL + Const.XIAO_QU_SHUO_API_PREFIX + "?kw=" + keyword;
                housingEstateService.handle(url, keyword);
                Date now = new Date();
                long delta = now.getTime() - begin.getTime();
                if (delta / 1000 - 50 * 60 > 0) {
                    break;
                }
            }
        }).start();
        return JsonRet.success();
    }
}