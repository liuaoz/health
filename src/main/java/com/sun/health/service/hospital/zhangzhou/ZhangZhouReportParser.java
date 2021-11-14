package com.sun.health.service.hospital.zhangzhou;

import com.sun.health.comm.ZhangZhouReportBasicItem;
import com.sun.health.comm.ZhangZhouReportItemTitle;
import com.sun.health.core.util.NumberUtil;
import com.sun.health.core.util.StringUtil;
import com.sun.health.entity.blood.BloodReportEntity;
import com.sun.health.repository.blood.BloodRepository;
import com.sun.health.service.AbstractService;
import com.tencentcloudapi.ocr.v20181119.models.Coord;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import com.tencentcloudapi.ocr.v20181119.models.TextDetection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ZhangZhouReportParser extends AbstractService {

    public static final int COMMON_DELTA = 30;

    @Autowired
    private BloodRepository bloodRepository;

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        String value = map.get(null);
        System.out.println(value);
    }

    public void parse(String content) {
        //1. 检测方法之后，进入检测项目
        //2. 构造一个map对象，项目名称作为key；value为一个数组，存放该项目的检测结果、参考值等
        //3. 记录项目、结果、参考值、单位、检测方法的x坐标
        //4. 遍历所有的识别文字。如果是项目名称的，这put到map中，同时创建一个list，把后续的文字依次存放到该list，直至下一个项目名称停止。
        //5. 遍历map，为每个key-value创建一个entity

        //basic info
        String patient = null, acquisitionTime = null, specimenType = null, inspectionPurpose = null, reportDate = null;
        //item title polygon
        Coord resultPolygon = null, referencePolygon = null, unitPolygon = null, inspectionMethodPolygon = null;


        Map<String, List<Text>> maps = new LinkedHashMap<>();

        GeneralBasicOCRResponse response = GeneralBasicOCRResponse.fromJsonString(content, GeneralBasicOCRResponse.class);

        boolean itemStart = false;
        String currentItemKey = null;
        if (Objects.nonNull(response) && !CollectionUtils.isEmpty(Arrays.asList(response.getTextDetections()))) {
            TextDetection[] textDetections = response.getTextDetections();

            for (TextDetection textDetection : textDetections) {

                // 获取或者姓名
                if (textDetection.getDetectedText().contains(ZhangZhouReportBasicItem.patient.getName())) {
                    patient = textDetection.getDetectedText();
                    patient = patient.replace(ZhangZhouReportBasicItem.patient.getName() + ":", "");
                }

                // 获取采集时间
                if (textDetection.getDetectedText().contains(ZhangZhouReportBasicItem.acquisitionTime.getName())) {
                    acquisitionTime = textDetection.getDetectedText();
                    acquisitionTime = acquisitionTime.replace(ZhangZhouReportBasicItem.acquisitionTime.getName() + ":", "");
                    reportDate = acquisitionTime.substring(0, 10);
                }

                // 获取标本种类
                if (textDetection.getDetectedText().contains(ZhangZhouReportBasicItem.specimenType.getName())) {
                    specimenType = textDetection.getDetectedText();
                    specimenType = specimenType.replace(ZhangZhouReportBasicItem.specimenType.getName() + ":", "");
                }

                // 获取检验目的
                if (textDetection.getDetectedText().contains(ZhangZhouReportBasicItem.inspectionPurpose.getName())) {
                    inspectionPurpose = textDetection.getDetectedText();
                    inspectionPurpose = inspectionPurpose.replace(ZhangZhouReportBasicItem.inspectionPurpose.getName() + ":", "");
                }


                if (textDetection.getDetectedText().equals(ZhangZhouReportItemTitle.result.getName())) {
                    resultPolygon = textDetection.getPolygon()[0];
                }
                if (textDetection.getDetectedText().equals(ZhangZhouReportItemTitle.reference.getName())) {
                    referencePolygon = textDetection.getPolygon()[0];
                }
                if (textDetection.getDetectedText().equals(ZhangZhouReportItemTitle.unit.getName())) {
                    unitPolygon = textDetection.getPolygon()[0];
                }
                if (textDetection.getDetectedText().equals(ZhangZhouReportItemTitle.inspectionMethod.getName())) {
                    inspectionMethodPolygon = textDetection.getPolygon()[0];
                    itemStart = true;
                    continue;
                }

                if (itemStart) {

                    if (isItem(textDetection.getDetectedText())) {
                        currentItemKey = textDetection.getDetectedText();
                        List<Text> itemRow = new ArrayList<>();
                        maps.put(currentItemKey, itemRow);
                    } else {
                        if (Objects.nonNull(currentItemKey) && Objects.nonNull(maps.get(currentItemKey))) {
                            maps.get(currentItemKey).add(new Text(textDetection.getDetectedText(), textDetection.getPolygon()[0].getX()));
                        }
                    }
                }

            }
        }
        if (Objects.isNull(resultPolygon) || Objects.isNull(referencePolygon) || Objects.isNull(unitPolygon) || Objects.isNull(inspectionMethodPolygon)) {
            logger.warn("解析的结果中，项目标题不完整！！！");
            return;
        }
//        logger.info("结果-x:" + resultPolygon.getX());
//        logger.info("参考范围-x:" + referencePolygon.getX());
//        logger.info("单位-x:" + unitPolygon.getX());
//        logger.info("检测方法-x:" + inspectionMethodPolygon.getX());

        Coord finalResultPolygon = resultPolygon;
        Coord finalReferencePolygon = referencePolygon;
        Coord finalUnitPolygon = unitPolygon;
        Coord finalInspectionMethodPolygon = inspectionMethodPolygon;
        String finalPatient = patient;
        String finalAcquisitionTime = acquisitionTime;

        String finalInspectionPurpose = inspectionPurpose;
        String finalSpecimenType = specimenType;
        String finalReportDate = reportDate;
        List<BloodReportEntity> entities = maps.entrySet().stream().map(entry -> {

            BloodReportEntity entity = new BloodReportEntity();
            String item = entry.getKey();
            List<Text> texts = entry.getValue();

            entity.setPatient(finalPatient);
            entity.setAcquisitionTime(finalAcquisitionTime);
            entity.setReportDate(finalReportDate);
            entity.setInspectionPurpose(finalInspectionPurpose);
            entity.setSpecimenType(finalSpecimenType);

            entity.setItem(item);

            for (Text text : texts) {
                boolean match = false;
                if (NumberUtil.isSimilar(text.getX(), finalResultPolygon.getX(), COMMON_DELTA)) {
                    entity.setResult(text.getValue());
                    match = true;
                }

                if (NumberUtil.isSimilar(text.getX(), finalReferencePolygon.getX(), 40)) {
                    entity.setReference(text.getValue());
                    match = true;
                }

                if (NumberUtil.isSimilar(text.getX(), finalUnitPolygon.getX(), COMMON_DELTA)) {
                    entity.setUnit(text.getValue());
                    match = true;
                }

                if (NumberUtil.isSimilar(text.getX(), finalInspectionMethodPolygon.getX(), COMMON_DELTA)) {
                    entity.setInspectionMethod(text.getValue());
                    match = true;
                }
                if (!match) {
                    logger.warn("项目:{},没有正常处理的识别文本：{}", item, text.getValue());
                }
            }
            return entity;
        }).collect(Collectors.toList());

        if (!entities.isEmpty()) {
            bloodRepository.saveAll(entities);
        }
    }

    public static boolean isItem(String text) {
        return !StringUtil.isEmpty(text) && StringUtil.isContainChinese(text) && (text.contains("(") || text.contains("}"));
    }

    static class Text {
        private String value;
        private Long x;

        public Text(String value, Long x) {
            this.value = value;
            this.x = x;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Long getX() {
            return x;
        }

        public void setX(Long x) {
            this.x = x;
        }
    }

}
