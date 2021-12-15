package com.sun.health.repository.bandao.good;

import com.sun.health.entity.bandao.good.GoodImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodImageRepository extends JpaRepository<GoodImageEntity,Long> {
}
