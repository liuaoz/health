package com.sun.health.repository.bandao.good;

import com.sun.health.entity.bandao.good.GoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<GoodEntity,Long> {

    List<GoodEntity> findBySaleable(boolean saleable);
}
