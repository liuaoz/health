package com.sun.health.repository.crawler;

import com.sun.health.entity.crawler.DaiFuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaiFuRepository extends JpaRepository<DaiFuEntity,Long> {
}
