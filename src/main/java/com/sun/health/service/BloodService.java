package com.sun.health.service;

import com.sun.health.core.comm.DataHolder;
import com.sun.health.core.util.DateUtil;
import com.sun.health.core.util.FileUtil;
import com.sun.health.core.util.StringUtil;
import com.sun.health.entity.blood.BloodReportEntity;
import com.sun.health.entity.ocr.OcrInfoEntity;
import com.sun.health.repository.blood.BloodReportRepository;
import com.sun.health.service.hospital.zhangzhou.ZhangZhouReportParser;
import com.sun.health.service.ocr.OcrInfoService;
import com.sun.health.service.tencent.TencentService;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import com.tencentcloudapi.ocr.v20181119.models.TextDetection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/22
 **/
@Service
public class BloodService extends AbstractService {

    //    private static final String PARENT_DIR = "/Users/stonechen/father/";
    private static final String PARENT_DIR = "D:\\report\\";

    @Autowired
    private BloodReportRepository bloodReportRepository;

    @Autowired
    private TencentService tencentService;

    @Autowired
    private OcrInfoService ocrService;

    @Autowired
    private ZhangZhouReportParser zhangZhouReportParser;

    private static List<String> titles = new ArrayList<>();
    private static Map<String, String> statusFlag = new HashMap<>();
    private static List<String> keywords = new ArrayList<>();

    static {
        titles.add("结果");
        titles.add("参考范围");

        statusFlag.put("↑", "偏高");
        statusFlag.put("↓", "偏低");
        statusFlag.put("阳性", "异常");
        statusFlag.put("阳", "异常");

        // unit
        keywords.add("秒");
        keywords.add("阴性");
        keywords.add("阳性");
        keywords.add("正常");
        keywords.add("镜检报告");
        keywords.add("未找到");
        keywords.add("仪器结果受干扰");
        keywords.add("以镜检结果为准");
        keywords.add("干化学报告");
        keywords.add("黄");
        keywords.add("清");
        keywords.add("+");
        keywords.add("++");
        keywords.add("+++");
        keywords.add("++++");
        keywords.add("+++++");
    }

    /**
     * 批量处理报告文件。调用第三方ocr接口并解析入库
     */
    public void handleZhangZhouReport() {

        //1. get orc result
        List<Long> ocrIds = ocrService.getAllIds();

        ocrIds.forEach(id -> {

            OcrInfoEntity ocrInfo = ocrService.getById(id);

            if (Objects.nonNull(ocrInfo)) {
                // parse ocr
                zhangZhouReportParser.parse(ocrInfo.getFileName(),ocrInfo.getJsonResponse());
            }

        });
    }

    public boolean delete(String reportDate) {
        bloodReportRepository.deleteByReportDate(reportDate);
        return true;
    }

    public void startHandle() {
        String reportFile = "D:\\report";
        File file = new File(reportFile);
        File[] files = file.listFiles();

        if (Objects.isNull(files)) {
            return;
        }
        Arrays.stream(files).forEach(t -> handle(t.getName()));
    }

    /**
     * 批量处理某一次检查报告，可能包含多张检查报告单
     *
     * @param reportDate 检查日期, 格式为：yyyyMMdd
     */
    @Transactional
    public void handle(String reportDate) {
        String parentDir = PARENT_DIR + reportDate;
        File file = new File(parentDir);
        File[] files = file.listFiles();

        List<String> failed = new ArrayList<>();

        if (files == null) {
            logger.warn("{} 没有检测报告单", reportDate);
            return;
        }
        //1.清理当天所以报告单
        delete(reportDate);

        Arrays.stream(files).forEach(k -> {
            try {
                parse(reportDate, DateUtil.fromYyyyMMdd(reportDate), FileUtil.toByteArrayByNio(k));
            } catch (Exception e) {
                failed.add(k.getName());
                logger.error("parse file error.file=" + k.getName(), e);
            }
//            list.forEach(t -> System.out.println(String.format("%-40s", t.getItem().trim()) + String.format("%-40s", t.getResult().trim()) + String.format("%-40s", t.getReference().trim())));
        });
        logger.info("{} 报告处理结果：[报告单总数：{}，失败的报告单：{}]", reportDate, files.length, failed.toArray());
    }

    /**
     * parse report image
     */
    public void parse(String reportDate, Date measurementTime, byte[] content) {

        List<BloodReportEntity> reportEntities = new ArrayList<>();

//        String content = "{\"TextDetections\":[{\"DetectedText\":\"项目\",\"Confidence\":99,\"Polygon\":[{\"X\":8,\"Y\":10},{\"X\":80,\"Y\":10},{\"X\":80,\"Y\":32},{\"X\":8,\"Y\":32}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":1}}\",\"ItemPolygon\":{\"X\":8,\"Y\":10,\"Width\":72,\"Height\":22},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"结果\",\"Confidence\":99,\"Polygon\":[{\"X\":206,\"Y\":11},{\"X\":269,\"Y\":13},{\"X\":268,\"Y\":35},{\"X\":205,\"Y\":33}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":8}}\",\"ItemPolygon\":{\"X\":206,\"Y\":11,\"Width\":63,\"Height\":22},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"参考范围\",\"Confidence\":90,\"Polygon\":[{\"X\":348,\"Y\":16},{\"X\":454,\"Y\":20},{\"X\":453,\"Y\":43},{\"X\":347,\"Y\":38}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":15}}\",\"ItemPolygon\":{\"X\":348,\"Y\":16,\"Width\":106,\"Height\":23},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"甲胎蛋白\",\"Confidence\":99,\"Polygon\":[{\"X\":3,\"Y\":49},{\"X\":83,\"Y\":48},{\"X\":83,\"Y\":69},{\"X\":3,\"Y\":71}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":2}}\",\"ItemPolygon\":{\"X\":3,\"Y\":49,\"Width\":80,\"Height\":21},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"1.9\",\"Confidence\":99,\"Polygon\":[{\"X\":206,\"Y\":59},{\"X\":237,\"Y\":59},{\"X\":237,\"Y\":78},{\"X\":206,\"Y\":78}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":9}}\",\"ItemPolygon\":{\"X\":206,\"Y\":59,\"Width\":31,\"Height\":19},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"<20.0ng/ml\",\"Confidence\":98,\"Polygon\":[{\"X\":347,\"Y\":56},{\"X\":444,\"Y\":60},{\"X\":443,\"Y\":79},{\"X\":346,\"Y\":74}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":15}}\",\"ItemPolygon\":{\"X\":347,\"Y\":56,\"Width\":97,\"Height\":19},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"癌胚抗原\",\"Confidence\":99,\"Polygon\":[{\"X\":2,\"Y\":88},{\"X\":83,\"Y\":87},{\"X\":83,\"Y\":109},{\"X\":2,\"Y\":110}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":3}}\",\"ItemPolygon\":{\"X\":2,\"Y\":88,\"Width\":81,\"Height\":22},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"14.9↑\",\"Confidence\":86,\"Polygon\":[{\"X\":208,\"Y\":99},{\"X\":263,\"Y\":99},{\"X\":263,\"Y\":119},{\"X\":208,\"Y\":119}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":10}}\",\"ItemPolygon\":{\"X\":208,\"Y\":99,\"Width\":55,\"Height\":20},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"<5ng/mL\",\"Confidence\":93,\"Polygon\":[{\"X\":347,\"Y\":96},{\"X\":418,\"Y\":98},{\"X\":417,\"Y\":117},{\"X\":346,\"Y\":115}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":15}}\",\"ItemPolygon\":{\"X\":347,\"Y\":96,\"Width\":71,\"Height\":19},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"糖类抗原19-9\",\"Confidence\":99,\"Polygon\":[{\"X\":2,\"Y\":128},{\"X\":125,\"Y\":128},{\"X\":125,\"Y\":150},{\"X\":2,\"Y\":150}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":4}}\",\"ItemPolygon\":{\"X\":2,\"Y\":128,\"Width\":123,\"Height\":22},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"336.0↑\",\"Confidence\":99,\"Polygon\":[{\"X\":207,\"Y\":140},{\"X\":273,\"Y\":140},{\"X\":273,\"Y\":160},{\"X\":207,\"Y\":160}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":10}}\",\"ItemPolygon\":{\"X\":207,\"Y\":140,\"Width\":66,\"Height\":20},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"<34U/mL,\",\"Confidence\":86,\"Polygon\":[{\"X\":347,\"Y\":135},{\"X\":417,\"Y\":137},{\"X\":416,\"Y\":155},{\"X\":347,\"Y\":153}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":15}}\",\"ItemPolygon\":{\"X\":347,\"Y\":135,\"Width\":70,\"Height\":18},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"三碘甲状腺原氨酸\",\"Confidence\":99,\"Polygon\":[{\"X\":3,\"Y\":167},{\"X\":167,\"Y\":170},{\"X\":167,\"Y\":192},{\"X\":3,\"Y\":189}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":5}}\",\"ItemPolygon\":{\"X\":3,\"Y\":167,\"Width\":164,\"Height\":22},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"1.8\",\"Confidence\":89,\"Polygon\":[{\"X\":209,\"Y\":180},{\"X\":239,\"Y\":180},{\"X\":239,\"Y\":198},{\"X\":209,\"Y\":198}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":10}}\",\"ItemPolygon\":{\"X\":209,\"Y\":180,\"Width\":30,\"Height\":18},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"1.3\",\"Confidence\":99,\"Polygon\":[{\"X\":347,\"Y\":176},{\"X\":378,\"Y\":176},{\"X\":378,\"Y\":192},{\"X\":347,\"Y\":192}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":16}}\",\"ItemPolygon\":{\"X\":347,\"Y\":176,\"Width\":31,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"--3.1nmol/L\",\"Confidence\":97,\"Polygon\":[{\"X\":379,\"Y\":177},{\"X\":492,\"Y\":178},{\"X\":492,\"Y\":195},{\"X\":379,\"Y\":194}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":16}}\",\"ItemPolygon\":{\"X\":379,\"Y\":177,\"Width\":113,\"Height\":17},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"甲状腺素\",\"Confidence\":99,\"Polygon\":[{\"X\":4,\"Y\":208},{\"X\":84,\"Y\":208},{\"X\":84,\"Y\":230},{\"X\":4,\"Y\":230}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":5}}\",\"ItemPolygon\":{\"X\":4,\"Y\":208,\"Width\":80,\"Height\":22},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"84.2\",\"Confidence\":99,\"Polygon\":[{\"X\":208,\"Y\":219},{\"X\":249,\"Y\":219},{\"X\":249,\"Y\":238},{\"X\":208,\"Y\":238}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":11}}\",\"ItemPolygon\":{\"X\":208,\"Y\":219,\"Width\":41,\"Height\":19},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"66.0-- 181.0\",\"Confidence\":97,\"Polygon\":[{\"X\":346,\"Y\":215},{\"X\":458,\"Y\":215},{\"X\":458,\"Y\":232},{\"X\":346,\"Y\":232}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":16}}\",\"ItemPolygon\":{\"X\":346,\"Y\":215,\"Width\":112,\"Height\":17},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"nmol/L\",\"Confidence\":95,\"Polygon\":[{\"X\":346,\"Y\":233},{\"X\":398,\"Y\":233},{\"X\":398,\"Y\":249},{\"X\":346,\"Y\":249}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":17}}\",\"ItemPolygon\":{\"X\":346,\"Y\":233,\"Width\":52,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"游离三碘甲状腺原氨酸\",\"Confidence\":99,\"Polygon\":[{\"X\":3,\"Y\":247},{\"X\":188,\"Y\":249},{\"X\":188,\"Y\":269},{\"X\":3,\"Y\":268}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":6}}\",\"ItemPolygon\":{\"X\":3,\"Y\":247,\"Width\":185,\"Height\":20},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"4.7\",\"Confidence\":94,\"Polygon\":[{\"X\":209,\"Y\":257},{\"X\":240,\"Y\":257},{\"X\":240,\"Y\":276},{\"X\":209,\"Y\":276}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":12}}\",\"ItemPolygon\":{\"X\":209,\"Y\":257,\"Width\":31,\"Height\":19},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"3.1--6.8pmol/L\",\"Confidence\":99,\"Polygon\":[{\"X\":346,\"Y\":253},{\"X\":491,\"Y\":254},{\"X\":491,\"Y\":271},{\"X\":346,\"Y\":270}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":18}}\",\"ItemPolygon\":{\"X\":346,\"Y\":253,\"Width\":145,\"Height\":17},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"游离甲状腺素\",\"Confidence\":92,\"Polygon\":[{\"X\":3,\"Y\":287},{\"X\":127,\"Y\":288},{\"X\":127,\"Y\":310},{\"X\":3,\"Y\":309}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":6}}\",\"ItemPolygon\":{\"X\":3,\"Y\":287,\"Width\":124,\"Height\":22},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"13.4\",\"Confidence\":99,\"Polygon\":[{\"X\":210,\"Y\":297},{\"X\":250,\"Y\":297},{\"X\":250,\"Y\":315},{\"X\":210,\"Y\":315}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":13}}\",\"ItemPolygon\":{\"X\":210,\"Y\":297,\"Width\":40,\"Height\":18},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"12.0--22.0\",\"Confidence\":99,\"Polygon\":[{\"X\":347,\"Y\":291},{\"X\":449,\"Y\":291},{\"X\":449,\"Y\":307},{\"X\":347,\"Y\":307}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":18}}\",\"ItemPolygon\":{\"X\":347,\"Y\":291,\"Width\":102,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"pmol/L\",\"Confidence\":97,\"Polygon\":[{\"X\":345,\"Y\":311},{\"X\":398,\"Y\":307},{\"X\":399,\"Y\":324},{\"X\":346,\"Y\":327}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":19}}\",\"ItemPolygon\":{\"X\":345,\"Y\":311,\"Width\":53,\"Height\":17},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"超敏促甲状腺激素\",\"Confidence\":99,\"Polygon\":[{\"X\":4,\"Y\":327},{\"X\":169,\"Y\":328},{\"X\":169,\"Y\":350},{\"X\":4,\"Y\":349}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":7}}\",\"ItemPolygon\":{\"X\":4,\"Y\":327,\"Width\":165,\"Height\":22},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"1.420\",\"Confidence\":99,\"Polygon\":[{\"X\":209,\"Y\":335},{\"X\":260,\"Y\":335},{\"X\":260,\"Y\":353},{\"X\":209,\"Y\":353}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":14}}\",\"ItemPolygon\":{\"X\":209,\"Y\":335,\"Width\":51,\"Height\":18},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"0.27--4.2\",\"Confidence\":96,\"Polygon\":[{\"X\":346,\"Y\":329},{\"X\":440,\"Y\":328},{\"X\":440,\"Y\":344},{\"X\":346,\"Y\":345}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":20}}\",\"ItemPolygon\":{\"X\":346,\"Y\":329,\"Width\":94,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"uIU/mL\",\"Confidence\":99,\"Polygon\":[{\"X\":346,\"Y\":345},{\"X\":398,\"Y\":345},{\"X\":398,\"Y\":361},{\"X\":346,\"Y\":361}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":20}}\",\"ItemPolygon\":{\"X\":346,\"Y\":345,\"Width\":52,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]}],\"Language\":\"zh\",\"Angel\":359.99,\"PdfPageSize\":0,\"RequestId\":\"5c18d6a3-d07b-46ea-99c8-3a0ea317e291\"}\n";
//        GeneralBasicOCRResponse response = JsonUtil.fromJson(content, GeneralBasicOCRResponse.class);

        GeneralBasicOCRResponse response = tencentService.basicOcr(content);

        if (Objects.nonNull(response)) {

            TextDetection[] textDetections = response.getTextDetections();

            List<Integer> itemIndex = new ArrayList<>();

            for (int i = 3; i < textDetections.length; i++) {

                TextDetection textDetection = textDetections[i];
                String detectedText = textDetection.getDetectedText();

                if (titles.contains(detectedText.trim())) {
                    continue;
                }

                if (StringUtil.isContainChinese(detectedText) && !StringUtil.contain(detectedText, keywords)) {
                    itemIndex.add(i);
                }
            }

            itemIndex.forEach(index -> {

                BloodReportEntity bloodReportEntity = new BloodReportEntity();
                bloodReportEntity.setItem(textDetections[index].getDetectedText());
                bloodReportEntity.setResult(textDetections[index + 1].getDetectedText());
                bloodReportEntity.setReportDate(reportDate);
                DataHolder<Boolean, String> holder = StringUtil.getContainItem(bloodReportEntity.getResult(), new ArrayList<>(statusFlag.keySet()));
                if (holder.getHasData()) {
                    bloodReportEntity.setStatus(statusFlag.get(holder.getData()));
                }
                StringBuilder sb = new StringBuilder();

                int i = index + 2;
                while (i < textDetections.length && (!StringUtil.isContainChinese(textDetections[i].getDetectedText()) || StringUtil.contain(textDetections[i].getDetectedText(), keywords))) {
                    sb.append(textDetections[i].getDetectedText());
                    i++;
                }
                bloodReportEntity.setReference(sb.toString());
                reportEntities.add(bloodReportEntity);
            });
            save(reportEntities);
        } else {
            logger.warn("Tencent basic ocr response is invalid.");
        }
    }

    public void save(List<BloodReportEntity> list) {
        bloodReportRepository.saveAll(list);
    }

    public List<BloodReportEntity> findByItem(String item) {
        return bloodReportRepository.findByItem(item);
    }

    public List<BloodReportEntity> findByReportDate(String reportDate) {
        return bloodReportRepository.findByReportDate(reportDate);
    }

}
