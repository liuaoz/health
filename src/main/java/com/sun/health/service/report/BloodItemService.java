package com.sun.health.service.report;

import com.sun.health.entity.blood.BloodItemEntity;
import com.sun.health.repository.blood.BloodItemRepository;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodItemService extends AbstractService {

    @Autowired
    private BloodItemRepository bloodItemRepository;

    public List<BloodItemEntity> getAll() {
        return bloodItemRepository.findAll();
    }


}
