package com.sun.health.repository.ocr;

import com.sun.health.entity.ocr.OcrInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcrInfoRepository extends JpaRepository<OcrInfoEntity, Long> {

    List<OcrInfoEntity> findByFileName(String fileName);

    @Query(value = "select id from tb_ocr_info", nativeQuery = true)
    List<Long> findAllIds();
}
