package com.sun.health.repository.blood;

import com.sun.health.entity.blood.IndicatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorRepository extends JpaRepository<IndicatorEntity, Long> {

    IndicatorEntity findByItem(String item);
}
