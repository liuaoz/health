package com.sun.health.dto.report;

import com.sun.health.dto.BaseDTO;

public class BloodDto extends BaseDTO {

    private String patient;

    private String item;

    private String result;

    private String reportDate;

    private String reference;

    private String inspectionPurpose;

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
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

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getInspectionPurpose() {
        return inspectionPurpose;
    }

    public void setInspectionPurpose(String inspectionPurpose) {
        this.inspectionPurpose = inspectionPurpose;
    }
}
