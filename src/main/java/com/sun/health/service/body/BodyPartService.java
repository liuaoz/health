package com.sun.health.service.body;

import com.sun.health.entity.body.BodyPartEntity;
import com.sun.health.entity.disease.DiseaseEntity;
import com.sun.health.repository.body.BodyPartRepository;
import com.sun.health.repository.disease.DiseaseRepository;
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
public class BodyPartService extends AbstractService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private BodyPartRepository bodyPartRepository;

    @PostConstruct
    public void parse() {

        List<BodyPartEntity> existsBodyParts = bodyPartRepository.findAll();

        Set<String> parts = new HashSet<>();

        List<DiseaseEntity> diseaseEntities = diseaseRepository.findAll();

        List<String> bodyParts = diseaseEntities.stream().map(DiseaseEntity::getBodyPart).collect(Collectors.toList());

        bodyParts.forEach(t -> {
            String[] split = t.split(",|，");
            List<String> asl = Arrays.asList(split);
            parts.addAll(asl);
        });

        List<BodyPartEntity> entities = new ArrayList<>();
        parts.forEach(t -> {
            BodyPartEntity entity = new BodyPartEntity();
            entity.setName(t);
            entities.add(entity);
        });

        if (existsBodyParts.size() != entities.size()) {
            bodyPartRepository.deleteAll();
            bodyPartRepository.saveAll(entities);
        }
    }
}
