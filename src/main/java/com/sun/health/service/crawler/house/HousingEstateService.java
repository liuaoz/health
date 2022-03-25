package com.sun.health.service.crawler.house;

import com.sun.health.core.util.JsonUtil;
import com.sun.health.entity.crawler.shelter.HousingEstateEntity;
import com.sun.health.entity.crawler.shelter.HousingEstateKwEntity;
import com.sun.health.repository.crawler.house.HousingEstateEwRepository;
import com.sun.health.repository.crawler.house.HousingEstateRepository;
import com.sun.health.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 上海 小区信息
 */
@Service
public class HousingEstateService extends AbstractService {

    //小区说  http://www.xiaoqushuo.com

    // url: http://www.xiaoqushuo.com/getyt?kw=we&cb=sh&callback=sh&_=1647702066917


    @Autowired
    private HousingEstateRepository housingEstateRepository;

    @Autowired
    private HousingEstateEwRepository housingEstateEwRepository;

    public List<HousingEstateEntity> handle(String url, String keyword) {

        if (existsKeyword(keyword)) {
            return new ArrayList<>();
        }

        HttpClient client = HttpClient.newBuilder()
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3100.0 Safari/537.36")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("keyword={};;resp={}", keyword, response.body());
            if (HttpStatus.valueOf(response.statusCode()).is2xxSuccessful()) {
                String respBody = response.body();
                String realData = respBody.substring(3, respBody.length() - 1);
                HousingEstateResp housingEstateResp = JsonUtil.fromJson(realData, HousingEstateResp.class);

                //有匹配
                List<HousingEstateDto> estateRespS = housingEstateResp.getS();
                if (Objects.nonNull(estateRespS)) {
                    List<HousingEstateEntity> housingEstateEntities = estateRespS.stream().map(this::map).collect(Collectors.toList());
                    save(housingEstateEntities);
                    return housingEstateEntities;
                }
                saveKeyword(keyword);
            }
        } catch (Exception e) {
            logger.error("get data from xiaoqushuo error. url=" + url, e);
        }
        return new ArrayList<>();

    }

    public boolean exists(String name, String address) {
        HousingEstateEntity housingEstate = housingEstateRepository.findByNameAndAddress(name, address);
        return Objects.nonNull(housingEstate);
    }

    public boolean existsKeyword(String keyword) {
        List<HousingEstateKwEntity> entities = housingEstateEwRepository.findByKeyword(keyword);
        return !CollectionUtils.isEmpty(entities);
    }

    public void saveKeyword(String keyword) {
        HousingEstateKwEntity kw = new HousingEstateKwEntity();
        kw.setKeyword(keyword);
        housingEstateEwRepository.save(kw);
    }

    private HousingEstateEntity map(HousingEstateDto dto) {
        HousingEstateEntity entity = new HousingEstateEntity();
        entity.setAddress(dto.getDizhi());
        entity.setCode(dto.getSid());
        entity.setName(dto.getIsb());
        entity.setDistrict("todo");
        entity.setLatitude(dto.getLat());
        entity.setLongitude(dto.getLng());
        entity.setSearch(dto.getSearch());
        entity.setTitle(dto.getTitle());
        return entity;
    }

    private void save(List<HousingEstateEntity> list) {
        List<HousingEstateEntity> tobeAdd = list.stream().filter(t -> !exists(t.getName(), t.getAddress())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(tobeAdd)) {
            housingEstateRepository.saveAll(tobeAdd);
        }
    }


}
