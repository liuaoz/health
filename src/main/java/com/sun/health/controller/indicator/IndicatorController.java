package com.sun.health.controller.indicator;

import com.sun.health.controller.BaseController;
import com.sun.health.dto.indicator.IndicatorRespDTO;
import com.sun.health.service.indicator.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/indicator")
public class IndicatorController extends BaseController {

    @Autowired
    private IndicatorService indicatorService;

    @GetMapping("/trend")
    public ResponseEntity<List<IndicatorRespDTO>> getTrend(@RequestParam String indicator) {

        return ResponseEntity.ok(indicatorService.getTrendByIndicator(indicator));
    }
}
