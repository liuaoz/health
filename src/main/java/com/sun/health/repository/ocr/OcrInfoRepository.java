package com.sun.health.repository.ocr;

import com.sun.health.entity.ocr.OcrInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcrInfoRepository extends JpaRepository<OcrInfoEntity, Long> {

    List<OcrInfoEntity> findByFileName(String fileName);

    @Query(value = "select id from tb_ocr_info", nativeQuery = true)
    List<Long> findAllIds();

    @Query(value = "select id from tb_ocr_info where parse_status = :parseStatus", nativeQuery = true)
    List<Long> findIds(@Param("parseStatus") String parseStatus);
}
