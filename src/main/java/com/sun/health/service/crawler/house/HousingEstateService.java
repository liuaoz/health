package com.sun.health.service.crawler.house;

import com.google.common.collect.Lists;
import com.sun.health.comm.Const;
import com.sun.health.core.util.JsonUtil;
import com.sun.health.entity.crawler.shelter.HousingEstateEntity;
import com.sun.health.entity.crawler.shelter.HousingEstateKwEntity;
import com.sun.health.repository.crawler.house.HousingEstateEwRepository;
import com.sun.health.repository.crawler.house.HousingEstateRepository;
import com.sun.health.service.AbstractService;
import com.sun.health.service.crawler.CrawlerSource;
import com.sun.health.service.crawler.house.anjuke.AnJuKeDto;
import com.sun.health.service.crawler.house.anjuke.AnJuKeResp;
import com.sun.health.service.crawler.house.tongcheng.TongChengDto;
import com.sun.health.service.crawler.house.tongcheng.TongChengResp;
import com.sun.health.service.crawler.house.xiaoqushuo.HousingEstateDto;
import com.sun.health.service.crawler.house.xiaoqushuo.HousingEstateResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
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

    public static final String AJK_URL = "https://shanghai.anjuke.com/esf-ajax/community/pc/autocomplete?city_id=11&type=2&kw=";
    public static final String TONGCHENG_URL = "https://sh.58.com/esf-ajax/community/pc/autocomplete/?city_id=2&type=2&kw=";


    @Autowired
    private HousingEstateRepository housingEstateRepository;

    @Autowired
    private HousingEstateEwRepository housingEstateEwRepository;

    public List<String> getNames(CrawlerSource crawlerSource){
        List<HousingEstateEntity> entities = housingEstateRepository.findBySource(crawlerSource.getName());
        return entities.stream().map(HousingEstateEntity::getName).collect(Collectors.toList());
    }

    public List<String> getNames(CrawlerSource crawlerSource,Long id){
        List<HousingEstateEntity> entities = housingEstateRepository.findBySourceAndIdGreaterThan(crawlerSource.getName(),id);
        return entities.stream().map(HousingEstateEntity::getName).collect(Collectors.toList());
    }


    public List<HousingEstateEntity> handleTongCheng(String keyword) {
        if (existsKeyword(keyword, CrawlerSource.TONGCHENG.getName())) {
            return new ArrayList<>();
        }
        HttpClient client = HttpClient.newBuilder()
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TONGCHENG_URL+ keyword))
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3100.0 Safari/537.36")
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("keyword={};;resp={}", keyword, httpResponse.body());
            if (HttpStatus.valueOf(httpResponse.statusCode()).is2xxSuccessful()) {
                String respBody = httpResponse.body();
                TongChengResp resp = JsonUtil.fromJson(respBody, TongChengResp.class);

                saveKeyword(keyword, CrawlerSource.TONGCHENG.getName());
                if ("ok".equals(resp.getStatus())) {
                    List<TongChengDto> tongChengDtos = resp.getData();
                    List<HousingEstateEntity> housingEstateEntities = tongChengDtos.stream().map(this::fromTongCheng).collect(Collectors.toList());
                    save(housingEstateEntities);
                    return housingEstateEntities;
                }
            } else {
                logger.warn("handle tongcheng warn....");
                return null;
            }
        } catch (IOException | InterruptedException e) {
            logger.error("handle tongcheng error.", e);
            return null;
        }
        return new ArrayList<>();
    }

    public List<HousingEstateEntity> handleAnJuKe(String keyword) {
        if (existsKeyword(keyword, CrawlerSource.AN_JU_KE.getName())) {
            return new ArrayList<>();
        }
        HttpClient client = HttpClient.newBuilder()
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AJK_URL + keyword))
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3100.0 Safari/537.36")
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("keyword={};;resp={}", keyword, httpResponse.body());
            if (HttpStatus.valueOf(httpResponse.statusCode()).is2xxSuccessful()) {
                String respBody = httpResponse.body();
                AnJuKeResp resp = JsonUtil.fromJson(respBody, AnJuKeResp.class);

                saveKeyword(keyword, CrawlerSource.AN_JU_KE.getName());
                if ("ok".equals(resp.getStatus())) {
                    List<AnJuKeDto> juKeDtos = resp.getData();
                    List<HousingEstateEntity> housingEstateEntities = juKeDtos.stream().map(this::fromAnJuKe).collect(Collectors.toList());
                    save(housingEstateEntities);
                    return housingEstateEntities;
                }
            } else {
                logger.warn("handle ajk warn....");
                return null;
            }
        } catch (IOException | InterruptedException e) {
            logger.error("handle ajk error.", e);
            return null;
        }
        return new ArrayList<>();
    }

    public List<HousingEstateEntity> handleXiaoQuShuo(String keyword) {

        String url = Const.XIAO_QU_SHUO_BASE_URL + Const.XIAO_QU_SHUO_API_PREFIX + "?kw=" + keyword;

        if (existsKeyword(keyword, CrawlerSource.XIAO_QU_SHUO.getName())) {
            return new ArrayList<>();
        }

        HttpClient client = HttpClient.newBuilder()
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3100.0 Safari/537.36")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("keyword={};;resp={}", keyword, response.body());
            if (HttpStatus.valueOf(response.statusCode()).is2xxSuccessful()) {
                String respBody = response.body();
                String realData = respBody.substring(3, respBody.length() - 1);
                HousingEstateResp housingEstateResp = JsonUtil.fromJson(realData, HousingEstateResp.class);

                saveKeyword(keyword, CrawlerSource.XIAO_QU_SHUO.getName());
                //有匹配
                List<HousingEstateDto> estateRespS = housingEstateResp.getS();
                if (Objects.nonNull(estateRespS)) {
                    List<HousingEstateEntity> housingEstateEntities = estateRespS.stream().map(this::fromXiaoQuShuo).collect(Collectors.toList());
                    save(housingEstateEntities);
                    return housingEstateEntities;
                }

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

    public boolean existsKeyword(String keyword, String source) {
        List<HousingEstateKwEntity> entities = housingEstateEwRepository.findByKeywordAndSource(keyword, source);
        return !CollectionUtils.isEmpty(entities);
    }

    public void saveKeyword(String keyword, String source) {
        HousingEstateKwEntity kw = new HousingEstateKwEntity();
        kw.setKeyword(keyword);
        kw.setSource(source);
        housingEstateEwRepository.save(kw);
    }

    private HousingEstateEntity fromAnJuKe(AnJuKeDto dto) {
        HousingEstateEntity entity = new HousingEstateEntity();
        entity.setAddress(dto.getAddress());
        entity.setName(dto.getName());
        entity.setSource(CrawlerSource.AN_JU_KE.getName());
        entity.setPrice(dto.getPrice());
        entity.setDistrict(dto.getAreaName());
        entity.setSourceId(String.valueOf(dto.getId()));
        return entity;
    }

    private HousingEstateEntity fromTongCheng(TongChengDto dto) {
        HousingEstateEntity entity = new HousingEstateEntity();
        entity.setAddress(dto.getAddress());
        entity.setName(dto.getName());
        entity.setSource(CrawlerSource.TONGCHENG.getName());
        entity.setPrice(dto.getPrice());
        entity.setDistrict(dto.getAreaName());
        entity.setSourceId(String.valueOf(dto.getId()));
        return entity;
    }

    private HousingEstateEntity fromXiaoQuShuo(HousingEstateDto dto) {
        HousingEstateEntity entity = new HousingEstateEntity();
        entity.setAddress(dto.getDizhi());
        entity.setCode(dto.getSid());
        entity.setName(dto.getIsb());
        entity.setLatitude(dto.getLat());
        entity.setLongitude(dto.getLng());
        entity.setSearch(dto.getSearch());
        entity.setTitle(dto.getTitle());
        int end = dto.getTitle().indexOf("]");
        entity.setDistrict(dto.getTitle().substring(1, end));
        entity.setSource(CrawlerSource.XIAO_QU_SHUO.getName());
        return entity;
    }

    private void save(List<HousingEstateEntity> list) {
        List<HousingEstateEntity> tobeAdd = list.stream().filter(t -> !exists(t.getName(), t.getAddress())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(tobeAdd)) {
            housingEstateRepository.saveAll(tobeAdd);
        }
    }


}
