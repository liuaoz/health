package com.sun.health.repository.blood;

import com.sun.health.entity.blood.BloodItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodItemRepository extends JpaRepository<BloodItemEntity, Long> {
}
