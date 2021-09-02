package com.sun.health.service.human;

import com.sun.health.entity.disease.DiseaseEntity;
import com.sun.health.entity.human.PeopleGroupEntity;
import com.sun.health.repository.disease.DiseaseRepository;
import com.sun.health.repository.human.PeopleGroupRepository;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/9/1
 **/
@Service
public class PeopleGroupService extends AbstractService {

    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private PeopleGroupRepository peopleGroupRepository;

    @PostConstruct
    public void parse() {

        List<DiseaseEntity> diseaseEntities = diseaseRepository.findAll();
        List<PeopleGroupEntity> existsPeopleGroups = peopleGroupRepository.findAll();

        List<String> groups = diseaseEntities.stream().map(DiseaseEntity::getGroupPeople).collect(Collectors.toList());

        List<PeopleGroupEntity> list = new ArrayList<>();

        Set<String> groupNames = groups.stream().filter(t -> !t.isEmpty()).collect(Collectors.toSet());

        groupNames.forEach(t -> {
            PeopleGroupEntity entity = new PeopleGroupEntity();
            entity.setName(t);
            list.add(entity);
        });

        if (existsPeopleGroups.size() != list.size()) {
            peopleGroupRepository.deleteAll();
            peopleGroupRepository.saveAll(list);
        }

    }
}
