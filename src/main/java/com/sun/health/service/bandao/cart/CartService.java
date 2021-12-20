package com.sun.health.service.bandao.cart;

import com.sun.health.dto.bandao.cart.CartDto;
import com.sun.health.dto.bandao.cart.CartGoodDto;
import com.sun.health.dto.bandao.good.GoodDto;
import com.sun.health.entity.BaseEntity;
import com.sun.health.entity.bandao.cart.CartEntity;
import com.sun.health.entity.bandao.good.GoodEntity;
import com.sun.health.repository.bandao.cart.CartRepository;
import com.sun.health.service.AbstractService;
import com.sun.health.service.bandao.good.GoodService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService extends AbstractService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private GoodService goodService;


    @NonNull
    public List<CartGoodDto> getCartList(Long userId) {

        List<CartEntity> cartEntities = this.getList(userId);

        List<Long> goodIds = cartEntities.stream().map(CartEntity::getGoodId).collect(Collectors.toList());

        if (!goodIds.isEmpty()) {
            List<GoodEntity> goodEntities = goodService.getByIds(goodIds);
            Map<Long, GoodDto> idEntityMap = goodEntities.stream().collect(Collectors.toMap(BaseEntity::getId, t -> {
                GoodDto goodDto = new GoodDto();
                BeanUtils.copyProperties(t, goodDto);
                return goodDto;
            }));

            return cartEntities.stream().map(t -> {
                CartGoodDto cartGoodDto = new CartGoodDto();
                BeanUtils.copyProperties(t, cartGoodDto);
                cartGoodDto.setGood(idEntityMap.get(t.getGoodId()));
                return cartGoodDto;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }


    /**
     * 列表
     */
    public List<CartEntity> getList(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    /**
     * 删除
     */
    public void delete(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    /**
     * 指定用户购物车中的指定商品
     */
    public CartEntity getByUserIdAndGoodId(Long userId, Long goodId) {
        return cartRepository.findByUserIdAndGoodId(userId, goodId);
    }

    /**
     * 保存
     */
    public void save(CartDto dto) {
        CartEntity cartEntity = this.getByUserIdAndGoodId(dto.getUserId(), dto.getGoodId());
        if (Objects.nonNull(cartEntity)) {
            cartEntity.setQuantity(cartEntity.getQuantity() + dto.getQuantity());
        } else {
            cartEntity = new CartEntity();
            BeanUtils.copyProperties(dto, cartEntity);
        }
        if (Objects.isNull(cartEntity.getId())) {
            cartEntity.setAddTime(new Date());
        }
        cartRepository.save(cartEntity);
    }
}
