package com.sun.health.service.bandao.good;

import com.sun.health.entity.bandao.good.GoodEntity;
import com.sun.health.repository.bandao.good.GoodRepository;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService extends AbstractService {

    @Autowired
    private GoodRepository goodRepository;

    /**
     * 商品列表
     */
    public List<GoodEntity> getGoods() {
        return goodRepository.findAll();
    }

    /**
     * 商品详情
     */
    public GoodEntity getById(Long goodId) {
        return goodRepository.getById(goodId);
    }

}
