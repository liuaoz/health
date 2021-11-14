package com.sun.health.repository.ocr;

import com.sun.health.entity.ocr.OcrInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcrInfoRepository extends JpaRepository<OcrInfoEntity, Long> {
}
