package com.sun.health.service.report;

import com.sun.health.core.util.StringUtil;
import com.sun.health.dto.report.BloodDto;
import com.sun.health.entity.blood.BloodReportEntity;
import com.sun.health.repository.blood.BloodReportRepository;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BloodReportService extends AbstractService {

    @Autowired
    private BloodReportRepository bloodReportRepository;

    public List<BloodReportEntity> getByCond(BloodDto dto) {

        Sort sort = Sort.by(Sort.Direction.DESC, "reportDate");

        return bloodReportRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtil.isEmpty(dto.getItem())) {
                predicates.add((criteriaBuilder.like(root.get("item"), "%" + dto.getItem() + "%")));
            }

            if (!StringUtil.isEmpty(dto.getReportDate())) {
                predicates.add((criteriaBuilder.equal(root.get("reportDate"), dto.getReportDate())));
            }

            if (!StringUtil.isEmpty(dto.getPatient())) {
                predicates.add((criteriaBuilder.equal(root.get("patient"), dto.getPatient())));
            }
            if (!StringUtil.isEmpty(dto.getInspectionPurpose())) {
                predicates.add((criteriaBuilder.like(root.get("inspectionPurpose"), "%" + dto.getInspectionPurpose() + "%")));
            }
            return criteriaQuery.where(predicates.toArray(new Predicate[0])).getRestriction();
        }, sort);
    }

    public List<BloodReportEntity> getByItem(String item) {
        return bloodReportRepository.findByItem(item);
    }

    public List<BloodReportEntity> getByPatient(String patient) {
        return bloodReportRepository.findByPatient(patient);
    }

    public List<BloodReportEntity> getAll() {
        return bloodReportRepository.findAll();
    }


}
