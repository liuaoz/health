package com.sun.health.repository.bandao.pay;

import com.sun.health.entity.bandao.pay.PayLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayLogRepository extends JpaRepository<PayLogEntity, Long> {
}
