package com.sun.health.service.indicator;

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

    public List<Object> getTrendByIndicator(String indicator) {

        List<BloodReportEntity> entities = bloodService.findByItem(indicator);

        return entities.stream().map(BloodReportEntity::getResult).collect(Collectors.toList());
    }
}
