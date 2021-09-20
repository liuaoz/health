package com.sun.health.controller.indicator;

import com.sun.health.controller.BaseController;
import com.sun.health.dto.indicator.IndicatorRespDTO;
import com.sun.health.entity.blood.BloodReportEntity;
import com.sun.health.service.indicator.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/indicator")
public class IndicatorController extends BaseController {

    @Autowired
    private IndicatorService indicatorService;

    @GetMapping("/trend")
    public ResponseEntity<List<IndicatorRespDTO>> getTrend(@RequestParam String indicator) {

        return ResponseEntity.ok(indicatorService.getTrendByIndicator(indicator));
    }

    @GetMapping("/reportDate/{reportDate}")
    public ResponseEntity<List<IndicatorRespDTO>> getByReportNo(@PathVariable String reportDate) {

        List<BloodReportEntity> reportByReportDate = indicatorService.getReportByReportDate(reportDate);
        List<IndicatorRespDTO> indicatorRespDTOS = reportByReportDate.stream().map(t -> {
            IndicatorRespDTO dto = new IndicatorRespDTO();
            dto.setIndicator(t.getItem());
            dto.setValue(t.getResult());
            dto.setReportDate(t.getReportDate());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(indicatorRespDTOS);
    }
}
