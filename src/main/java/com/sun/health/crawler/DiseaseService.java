package com.sun.health.crawler;

import com.sun.health.entity.crawler.DaiFuEntity;
import com.sun.health.entity.disease.DiseaseEntity;
import com.sun.health.repository.crawler.DaiFuRepository;
import com.sun.health.repository.disease.DiseaseRepository;
import com.sun.health.service.AbstractService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiseaseService extends AbstractService {

    public static final String userAgent =
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36";

    public static final String cookie =
            "Hm_lvt_9e22985df06deed62100d2beef9fb177=1630330763; Hm_lvt_2739afef62965c18b48103229be36bf6=1630330763; Hm_lpvt_2739afef62965c18b48103229be36bf6=1630332475; Hm_lpvt_9e22985df06deed62100d2beef9fb177=1630332475";

    @Autowired
    private DaiFuRepository daiFuRepository;

    @Autowired
    private DiseaseRepository diseaseRepository;


    public void parseDiseases() {
        List<DaiFuEntity> daiFuEntities = daiFuRepository.findAll();

        daiFuEntities.forEach(daiFuEntity -> {
            parseEachDisease(daiFuEntity.getUrl(), daiFuEntity.getName());
        });
    }


    private void parseEachDisease(String url, String diseaseName) {

        try {
            Connection.Response response = Jsoup.connect(url)
                    .userAgent(userAgent)
                    .cookie("Cookie", cookie)
                    .execute();

            Document document = response.parse();

            Elements itemEles = document.select("li[class=fl]");

            List<String> itemValues = new ArrayList<>();
            DiseaseEntity diseaseEntity = new DiseaseEntity();

            itemEles.forEach(e -> {
                String itemName = e.getElementsByTag("i").first().text();
                String itemValue = e.getElementsByTag("span").first().text();
                itemValues.add(itemValue);
            });


            //1. base info
            diseaseEntity.setName(diseaseName);
            diseaseEntity.setAlias(itemValues.get(1));
            diseaseEntity.setEnName(itemValues.get(2));
            diseaseEntity.setKeyWords(itemValues.get(3));
            diseaseEntity.setBodyPart(itemValues.get(4));
            diseaseEntity.setDepartment(itemValues.get(5));
            diseaseEntity.setSymptom(itemValues.get(6));
            diseaseEntity.setTreatment(itemValues.get(7));
            diseaseEntity.setGroupPeople(itemValues.get(8));
            diseaseEntity.setRelatedDrugs(itemValues.get(9));
            diseaseEntity.setTreatmentCost(itemValues.get(10));
            diseaseEntity.setCommonDisease(itemValues.get(11));
            diseaseEntity.setRelatedSurgery(itemValues.get(12));

            //2. + intro.shtml
            diseaseEntity.setIntro(getDiseaseItem(url + "intro.shtml"));

            //3. + pathogeny.shtml
            diseaseEntity.setPathogeny(getDiseaseItem(url + "pathogeny.shtml"));

            //4. + symptom.shtml
            diseaseEntity.setSymptom(getDiseaseItem(url + "symptom.shtml"));

            //5. + checkup.shtml
            diseaseEntity.setCheckup(getDiseaseItem(url + "checkup.shtml"));

            //6. + distinguish.shtml
            diseaseEntity.setDistinguish(getDiseaseItem(url + "distinguish.shtml"));

            //7. + syndrome.shtml
            diseaseEntity.setSyndrome(getDiseaseItem(url + "syndrome.shtml"));

            //8. + therapy.shtml
            diseaseEntity.setTherapy(getDiseaseItem(url + "therapy.shtml"));

            //9. + prevent.shtml
            diseaseEntity.setPrevent(getDiseaseItem(url + "prevent.shtml"));

            diseaseRepository.save(diseaseEntity);


        } catch (IOException e) {
            logger.error("parse url error. url=" + url, e);
        }
    }

    private String getDiseaseItem(String targetUrl) {

        try {
            Connection.Response response = Jsoup.connect(targetUrl)
                    .userAgent(userAgent)
                    .cookie("Cookie", cookie)
                    .execute();
            Document document = response.parse();

            Elements elements = document.select("p[class=fl w100p fs0d8r c999 reason_intro]");

            return elements.first().text();

        } catch (IOException e) {
            logger.error("getDiseaseItem error, url=" + targetUrl, e);
        }
        return null;
    }

    /**
     * http://lxxgm.d.51daifu.com/
     */
    public void crawlDiseaseList() {

        String url = "https://disease.51daifu.com/char";
//        String url = "https://www.baidu.com";

        try {
            Connection.Response response = Jsoup.connect(url)
                    .userAgent(userAgent)
                    .cookie("Cookie", cookie)
                    .execute();

            Document document = response.parse();

            Elements listEles = document.select("li[class=fl cont_item c333 fs0d9r pt0d4r mb0d4r]");

//            Elements listEles = document.getElementsByClass("fl cont_item c333 fs0d9r pt0d4r mb0d4r");

            logger.info("list={}", listEles);
            List<DaiFuEntity> list = new ArrayList<>();

            listEles.forEach(t -> {
                String hrefUrl = t.getElementsByTag("a").first().attr("href");
                String name = t.text();
                DaiFuEntity daiFuEntity = new DaiFuEntity();
                daiFuEntity.setName(name);
                daiFuEntity.setUrl(hrefUrl);
                list.add(daiFuEntity);
            });

            daiFuRepository.saveAll(list);


        } catch (IOException e) {
            logger.error("error: ", e);
        }


    }
}

