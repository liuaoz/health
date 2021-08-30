package com.sun.health.entity.disease;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_disease")
public class DiseaseEntity extends BaseEntity {

    /**
     * 名称
     */
    private String name;

    /**
     * 别名
     */
    private String alias;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 就诊科室
     */
    private String department;

    /**
     * 发病部位
     */
    private String bodyPart;

    /**
     * 关键症状体征
     */
    private String keySymptom;

    /**
     * 治疗方法
     */
    private String treatment;

    /**
     * 相关药品
     */
    private String relatedDrugs;

    /**
     * 多发人群
     */
    private String groupPeople;

    /**
     * 常见疾病
     */
    private String commonDisease;

    /**
     * 相关手术
     */
    private String relatedSurgery;

    /**
     * 治疗费用
     */
    private String treatmentCost;

    /**
     * 疾病关键词
     */
    private String keyWords;

    //------

    /**
     * 概述/简介
     */
    @Column(columnDefinition = "text")
    private String intro;

    /**
     * 病因病理
     */
    @Column(columnDefinition = "text")
    private String pathogeny;

    /**
     * 病状特征
     */
    @Column(columnDefinition = "text")
    private String symptom;

    /**
     * 检查化验
     */
    @Column(columnDefinition = "text")
    private String checkup;

    /**
     * 鉴别诊断
     */
    @Column(columnDefinition = "text")
    private String distinguish;

    /**
     * 并发症
     */
    @Column(columnDefinition = "text")
    private String syndrome;

    /**
     * 治疗用药
     */
    @Column(columnDefinition = "text")
    private String therapy;

    /**
     * 预防保健
     */
    @Column(columnDefinition = "text")
    private String prevent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String getKeySymptom() {
        return keySymptom;
    }

    public void setKeySymptom(String keySymptom) {
        this.keySymptom = keySymptom;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getRelatedDrugs() {
        return relatedDrugs;
    }

    public void setRelatedDrugs(String relatedDrugs) {
        this.relatedDrugs = relatedDrugs;
    }

    public String getGroupPeople() {
        return groupPeople;
    }

    public void setGroupPeople(String groupPeople) {
        this.groupPeople = groupPeople;
    }

    public String getCommonDisease() {
        return commonDisease;
    }

    public void setCommonDisease(String commonDisease) {
        this.commonDisease = commonDisease;
    }

    public String getRelatedSurgery() {
        return relatedSurgery;
    }

    public void setRelatedSurgery(String relatedSurgery) {
        this.relatedSurgery = relatedSurgery;
    }

    public String getTreatmentCost() {
        return treatmentCost;
    }

    public void setTreatmentCost(String treatmentCost) {
        this.treatmentCost = treatmentCost;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPathogeny() {
        return pathogeny;
    }

    public void setPathogeny(String pathogeny) {
        this.pathogeny = pathogeny;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getCheckup() {
        return checkup;
    }

    public void setCheckup(String checkup) {
        this.checkup = checkup;
    }

    public String getDistinguish() {
        return distinguish;
    }

    public void setDistinguish(String distinguish) {
        this.distinguish = distinguish;
    }

    public String getSyndrome() {
        return syndrome;
    }

    public void setSyndrome(String syndrome) {
        this.syndrome = syndrome;
    }

    public String getTherapy() {
        return therapy;
    }

    public void setTherapy(String therapy) {
        this.therapy = therapy;
    }

    public String getPrevent() {
        return prevent;
    }

    public void setPrevent(String prevent) {
        this.prevent = prevent;
    }
}
