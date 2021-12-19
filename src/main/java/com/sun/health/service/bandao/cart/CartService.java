package com.sun.health.service.bandao.cart;

import com.sun.health.dto.bandao.cart.CartDto;
import com.sun.health.entity.bandao.cart.CartEntity;
import com.sun.health.repository.bandao.cart.CartRepository;
import com.sun.health.service.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CartService extends AbstractService {

    @Autowired
    private CartRepository cartRepository;

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
     * 保存
     */
    public void save(CartDto dto) {
        CartEntity entity = new CartEntity();
        BeanUtils.copyProperties(dto, entity);

        if (Objects.isNull(dto.getId())) {
            entity.setAddTime(new Date());
        }
        cartRepository.save(entity);
    }
}
