package com.sun.health.service.bandao.good;

import com.sun.health.entity.bandao.cart.CartEntity;
import com.sun.health.entity.bandao.good.GoodEntity;
import com.sun.health.repository.bandao.good.GoodRepository;
import com.sun.health.service.AbstractService;
import com.sun.health.service.bandao.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodService extends AbstractService {

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private CartService cartService;

    /**
     * 商品列表
     */
    public List<GoodEntity> getGoods() {
        return goodRepository.findAll();
    }

    /**
     * 商品列表
     *
     * @param goodIds 商品id列表
     */
    public List<GoodEntity> getByIds(List<Long> goodIds) {
        return goodRepository.findAllById(goodIds);
    }

    /**
     * 商品详情
     */
    public GoodEntity getById(Long goodId) {
        return goodRepository.getById(goodId);
    }


}
