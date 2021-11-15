package com.sun.health.controller.report;

import com.sun.health.controller.BaseController;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.dto.report.BloodDto;
import com.sun.health.entity.blood.BloodReportEntity;
import com.sun.health.service.report.BloodReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportController extends BaseController {

    @Autowired
    private BloodReportService bloodReportService;


    @GetMapping("/item/{item}")
    public JsonRet<List<BloodDto>> get(@PathVariable String item) {

        List<BloodReportEntity> entities = bloodReportService.getByItem(item);
        List<BloodDto> dtoList = entities.stream().map(t -> {
            BloodDto dto = new BloodDto();
            BeanUtils.copyProperties(t, dto);
            return dto;
        }).collect(Collectors.toList());
        return JsonRet.success(dtoList);
    }

}
