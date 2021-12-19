package com.sun.health.repository.bandao.cart;

import com.sun.health.entity.bandao.cart.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    List<CartEntity> findByUserId(Long userId);
}
