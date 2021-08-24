package com.sun.health.entity.blood;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/21
 **/
@Entity
@Table(name = "tb_blood_report")
public class BloodReportEntity extends BaseEntity {

    /**
     * 患者
     */
    private String patient;

    /**
     * 检测时间
     */
    private Date measurementTime;

    /**
     * 检测日期
     */
    private String reportDate;

    /**
     * 指标名称
     */
    private String item;

    /**
     * 指标结果
     */
    private String result;

    /**
     * 指标参考值
     */
    private String reference;

    /**
     * 正常(normal)，偏高(high)，偏低(low)，异常(abnormal)...
     */
    private String status;

    /**
     * 检验师
     */
    private String inspector;

    /**
     * 核验者
     */
    private String checker;

    /**
     * 医师
     */
    private String doctor;

    /**
     * 科别
     */
    private String category;

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(Date measurementTime) {
        this.measurementTime = measurementTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
