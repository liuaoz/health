package com.sun.health.service.indicator;

import com.sun.health.dto.indicator.IndicatorRespDTO;
import com.sun.health.entity.blood.BloodReportEntity;
import com.sun.health.service.BloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndicatorService {

    @Autowired
    private BloodService bloodService;

    public List<IndicatorRespDTO> getTrendByIndicator(String indicator) {

        List<BloodReportEntity> entities = bloodService.findByItem(indicator);

        return entities.stream().map(t -> {
            IndicatorRespDTO dto = new IndicatorRespDTO();
            dto.setIndicator(indicator);
            dto.setValue(t.getResult());
            dto.setReportDate(t.getReportDate());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<BloodReportEntity> getReportByReportDate(String reportDate) {
        return bloodService.findByReportDate(reportDate);
    }
}
