package com.sun.health.service.report;

import com.sun.health.entity.blood.BloodReportEntity;
import com.sun.health.repository.blood.BloodRepository;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodReportService extends AbstractService {

    @Autowired
    private BloodRepository bloodRepository;

    public List<BloodReportEntity> getByItem(String item) {
        return bloodRepository.findByItem(item);
    }

    public List<BloodReportEntity> getByPatient(String patient) {
        return bloodRepository.findByPatient(patient);
    }
}
