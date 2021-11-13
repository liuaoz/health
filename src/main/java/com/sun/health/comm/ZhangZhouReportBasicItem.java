package com.sun.health.comm;

import java.util.Arrays;
import java.util.Objects;

public enum ZhangZhouReportBasicItem {

    patient("姓名"),
    sex("性别"),
    age("年龄"),
    bedNo("床号"),
    clinicalDiagnosis("临床诊断"),
    applicationForm("申请单据"),
    medicalNo("病历号"),
    doctor("送检医生"),
    category("科别"),
    specimenType("标本种类"),
    sampleNo("样本编号"),
    receiver("接收人员"),
    inspectionMachine("检验仪器"),
    inspectionPurpose("检验目的"),
    remark("备注"),
    applicationTime("申请时间"),
    acquisitionTime("采集时间"),
    receivingTime("接收时间");

    private final String name;

    ZhangZhouReportBasicItem(String name) {
        this.name = name;
    }

    public static boolean fuzzyMatchingContainDefine(String name) {
        if (Objects.isNull(name)) {
            return false;
        }
        return Arrays.stream(values()).anyMatch(t -> name.contains(t.name));
    }

    public static boolean fuzzyMatching(String name) {
        if (Objects.isNull(name)) {
            return false;
        }
        return Arrays.stream(values()).anyMatch(t -> t.name.contains(name) || name.contains(t.name));
    }
}
