package com.sun.health.repository.blood;

import com.sun.health.entity.blood.BloodReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/22
 **/
@Repository
public interface BloodReportRepository extends JpaRepository<BloodReportEntity, Long>, JpaSpecificationExecutor<BloodReportEntity> {

    int deleteByReportDate(String reportDate);

    List<BloodReportEntity> findByItem(String item);

    List<BloodReportEntity> findByPatient(String patient);

    List<BloodReportEntity> findByReportDate(String reportDate);

}
