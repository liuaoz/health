package com.sun.health.repository.disease;

import com.sun.health.entity.disease.DiseaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<DiseaseEntity, Long> {

    DiseaseEntity findByName(String name);
}
