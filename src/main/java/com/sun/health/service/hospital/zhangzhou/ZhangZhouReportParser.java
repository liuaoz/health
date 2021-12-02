package com.sun.health.service.hospital.zhangzhou;

import com.sun.health.comm.KeyValueHolder;
import com.sun.health.comm.ZhangZhouReportBasicItem;
import com.sun.health.comm.ZhangZhouReportItemTitle;
import com.sun.health.core.util.NumberUtil;
import com.sun.health.core.util.StringUtil;
import com.sun.health.entity.blood.BloodReportEntity;
import com.sun.health.repository.blood.BloodReportRepository;
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
    private BloodReportRepository bloodRepository;

    public void parse(String fileName, String content) {
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
                    if (acquisitionTime.length()>=10){
                        reportDate = acquisitionTime.substring(0, 10);
                    }

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
            logger.warn("{}解析的结果中，项目标题不完整！！！", fileName);
            return;
        }
        logger.info("文件:{},标题栏X坐标----[结果:{}|参考范围:{}|单位:{}|检测方法:{}]", fileName, resultPolygon.getX(), referencePolygon.getX(), unitPolygon.getX(), inspectionMethodPolygon.getX());

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
            entity.setFileName(fileName);

            KeyValueHolder<String, String> kv = filterItem(item, fileName);
            entity.setItem(kv.getKey());
            entity.setItemAbbr(kv.getValue());

            for (Text text : texts) {
                boolean match = false;
                if (NumberUtil.isSimilar(text.getX(), finalResultPolygon.getX(), COMMON_DELTA)) {
                    entity.setResult(text.getValue());
                    match = true;
                }

                if (NumberUtil.isSimilar(text.getX(), finalReferencePolygon.getX(), 70)) {
                    entity.setReference(text.getValue());
                    match = true;
                }

                if (NumberUtil.isSimilar(text.getX(), finalUnitPolygon.getX(), COMMON_DELTA)) {
                    entity.setUnit(text.getValue());
                    match = true;
                }

                if (NumberUtil.isSimilar(text.getX(), finalInspectionMethodPolygon.getX(), 70)) {
                    entity.setInspectionMethod(text.getValue());
                    match = true;
                }
                if (!match) {
                    logger.warn("文件:{},项目:{},文本:{},X坐标:{}", fileName, item, text.getValue(), text.getX());
                }
            }
            return entity;
        }).collect(Collectors.toList());

        if (!entities.isEmpty()) {
            bloodRepository.saveAll(entities);
        }
    }

    /**
     * 处理项目名称。由ocr解析误差造成的错误
     *
     * @param item 项目名称(指标名称)
     */
    public KeyValueHolder<String, String> filterItem(String item, String fileName) {

        String itemRealName = item;//指标名称
        String abbr = null;// 指标简称（英文代码简称）
        //1. 处理前缀。 去除前面的数字，可能1位或者两位数字
        if (NumberUtil.isNumeric(itemRealName.charAt(0))) {
            itemRealName = itemRealName.substring(1);
            if (NumberUtil.isNumeric(itemRealName.charAt(0))) {
                itemRealName = itemRealName.substring(1);
            }
//            logger.info("文件:{},项目名称:{},前缀处理后结果为：{}", fileName, item, itemRealName);
        }
        //2. 处理后缀。 去掉尾部的代码简称
        int i = itemRealName.indexOf("(");
        if (i != -1) {
            String tail = itemRealName.substring(i + 1, itemRealName.length() - 1);
            itemRealName = itemRealName.substring(0, i);
            abbr = tail.replaceAll(":", "").replaceAll("\\)", "");
//            logger.info("文件:{},项目名称:{},后缀处理后结果为：{}", fileName, item, itemRealName);
        }
        //3. 特殊字符处理。 去掉*
        itemRealName = itemRealName.replaceAll("\\*", "");
        return new KeyValueHolder<>(itemRealName, abbr);
    }

    public boolean isItem(String text) {
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
