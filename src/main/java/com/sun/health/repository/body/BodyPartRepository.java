package com.sun.health.repository.body;

import com.sun.health.entity.body.BodyPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/9/1
 **/
@Repository
public interface BodyPartRepository extends JpaRepository<BodyPartEntity, Long> {
}
