package com.sun.health.repository.bandao.cart;

import com.sun.health.entity.bandao.cart.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    List<CartEntity> findByUserId(Long userId);

    CartEntity findByUserIdAndGoodId(Long userId, Long goodId);

    @Transactional
    @Modifying()
    @Query(value = "update tb_cart set selected = ?1 where user_id = ?2",nativeQuery = true)
    void updateSelected(boolean selected, Long userId);
}
