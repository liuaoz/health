package com.sun.health.service.indicator;

import com.sun.health.dto.indicator.IndicatorRespDTO;
import com.sun.health.entity.blood.BloodReportEntity;
import com.sun.health.entity.blood.IndicatorEntity;
import com.sun.health.repository.blood.IndicatorRepository;
import com.sun.health.service.BloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class IndicatorService {

    @Autowired
    private BloodService bloodService;

    @Autowired
    private IndicatorRepository indicatorRepository;

    public void add(IndicatorEntity entity) {
        IndicatorEntity item = get(entity.getItem());
        if (Objects.isNull(item)) {
            indicatorRepository.save(entity);
        }
    }

    public IndicatorEntity get(String item) {
        return indicatorRepository.findByItem(item);
    }


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
