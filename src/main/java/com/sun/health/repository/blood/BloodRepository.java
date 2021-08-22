package com.sun.health.repository.blood;

import com.sun.health.entity.blood.BloodReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/22
 **/
@Repository
public interface BloodRepository extends JpaRepository<BloodReportEntity, Long> {
}
