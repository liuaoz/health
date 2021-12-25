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

    /**
     * 生成订单后，删除购物车中的选中商品
     */
    public boolean removeSelectedCart(Long userId) {
        cartRepository.deleteByUserIdAndSelected(userId, true);
        return true;
    }

    /**
     * 获取选中的商品
     */
    public List<CartEntity> getSelectedCart(Long userId) {
        return cartRepository.findByUserIdAndSelected(userId, true);
    }


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
     * 新加入购物车
     */
    public void add(CartDto dto) {
        CartEntity cartEntity = new CartEntity();
        BeanUtils.copyProperties(dto, cartEntity);
        cartEntity.setAddTime(new Date());
        cartRepository.save(cartEntity);
    }

    /**
     * 更新购物车
     */
    public void update(CartDto dto) {
        CartEntity cartEntity = this.getByUserIdAndGoodId(dto.getUserId(), dto.getGoodId());
        BeanUtils.copyProperties(dto, cartEntity);
        cartRepository.save(cartEntity);
    }

    /**
     * 保存
     */
    public void save(CartDto dto) {
        if (Objects.isNull(dto.getId())) {
            add(dto);
        } else {
            update(dto);
        }
    }

    /**
     * 处理全选/反选
     */
    public void handleSelectAll(boolean selectAll, Long userId) {
        cartRepository.updateSelected(selectAll, userId);
    }
}
