package com.sun.health.service.hospital;

import com.sun.health.entity.disease.DiseaseEntity;
import com.sun.health.entity.hospital.DepartmentEntity;
import com.sun.health.repository.disease.DiseaseRepository;
import com.sun.health.repository.hospital.DepartmentRepository;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/9/1
 **/
@Service
public class DepartmentService extends AbstractService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostConstruct
    public void parse() {

        List<DiseaseEntity> diseaseEntities = diseaseRepository.findAll();
        List<DepartmentEntity> existsDepartments = departmentRepository.findAll();

        List<String> departments = diseaseEntities.stream().map(DiseaseEntity::getDepartment).collect(Collectors.toList());

        Set<String> departmentSets = new HashSet<>();

        departments.forEach(d -> {

            String[] split = d.split("、|，");

            List<String> asList = Arrays.asList(split);

            departmentSets.addAll(asList);
        });

        List<DepartmentEntity> departmentEntities = new ArrayList<>();
        departmentSets.stream().filter(t -> !t.isEmpty()).forEach(t -> {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setName(t);
            departmentEntities.add(departmentEntity);
        });

        if (existsDepartments.size() != departmentEntities.size()) {
            departmentRepository.deleteAll();
            departmentRepository.saveAll(departmentEntities);
        }

    }
}
