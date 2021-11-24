package com.sun.health.controller.report;

import com.sun.health.controller.BaseController;
import com.sun.health.core.comm.JsonRet;
import com.sun.health.dto.report.BloodDto;
import com.sun.health.entity.blood.BloodReportEntity;
import com.sun.health.service.report.BloodReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportController extends BaseController {

    @Autowired
    private BloodReportService bloodReportService;

    @PostMapping("/blood")
    public JsonRet<List<BloodDto>> get(@RequestBody BloodDto dto) {

        List<BloodReportEntity> entities = bloodReportService.getByCond(dto);

        return JsonRet.success(toDto(entities));
    }

    @GetMapping("/blood/inspectionPurposes")
    public JsonRet<List<String>> getInspectionPurposes() {
        List<String> list = List.of(
                "请选择",
                "肌钙蛋白I",
                "降钙素原",
                "心肌酶",
                "血常规(六分类)",
                "凝血四项",
                "血浆D2聚体",
                "血气分析+电解质",
                "肝肾功电解质",
                "B型钠尿肽测定",
                "CA199+CEA，AFP，CA125"
        );
        return JsonRet.success(list);
    }

    @GetMapping("/blood/item/{item}")
    public JsonRet<List<BloodDto>> getByItem(@PathVariable String item) {
        List<BloodReportEntity> entities = bloodReportService.getByItem(item);
        return JsonRet.success(toDto(entities));
    }

    @GetMapping("/blood/patient/{patient}")
    public JsonRet<List<BloodDto>> getByPatient(@PathVariable String patient) {
        List<BloodReportEntity> entities = bloodReportService.getByPatient(patient);
        return JsonRet.success(toDto(entities));
    }

    private List<BloodDto> toDto(List<BloodReportEntity> entities) {
        return entities.stream().map(t -> {
            BloodDto dto = new BloodDto();
            BeanUtils.copyProperties(t, dto);
            return dto;
        }).collect(Collectors.toList());
    }

}
