package com.sun.health.repository.crawler.house;

import com.sun.health.entity.crawler.shelter.HousingEstateKwEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingEstateEwRepository extends JpaRepository<HousingEstateKwEntity,Long> {

    List<HousingEstateKwEntity> findByKeyword(String keyword);
}
