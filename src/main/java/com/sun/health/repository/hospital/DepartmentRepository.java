package com.sun.health.repository.hospital;

import com.sun.health.entity.hospital.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/9/1
 **/
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
