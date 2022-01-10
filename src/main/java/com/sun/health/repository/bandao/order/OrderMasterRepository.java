package com.sun.health.repository.bandao.order;

import com.sun.health.entity.bandao.order.OrderMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMasterEntity,Long> {

    List<OrderMasterEntity> findByUserId(Long userId);

    OrderMasterEntity findByOrderNo(String orderNo);
}
