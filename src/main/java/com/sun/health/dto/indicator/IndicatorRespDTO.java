package com.sun.health.dto.indicator;

import com.sun.health.dto.BaseDTO;

public class IndicatorRespDTO extends BaseDTO {

    private String indicator;

    private String value;

    private String reportDate;

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
}
