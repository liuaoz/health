package com.sun.health.repository.people;

import com.sun.health.entity.people.PeopleGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/9/1
 **/
@Repository
public interface PeopleGroupRepository extends JpaRepository<PeopleGroupEntity, Long> {
}
