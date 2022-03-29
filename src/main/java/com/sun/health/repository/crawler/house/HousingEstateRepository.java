package com.sun.health.repository.crawler.house;

import com.sun.health.entity.crawler.shelter.HousingEstateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingEstateRepository extends JpaRepository<HousingEstateEntity, Long> {

    HousingEstateEntity findByNameAndAddress(String name, String address);

    List<HousingEstateEntity> findBySource(String source);

    List<HousingEstateEntity> findBySourceAndIdGreaterThan(String source, Long id);

    void deleteByNameAndSource(String name, String source);

}
