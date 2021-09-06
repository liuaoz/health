package com.sun.health.dto.indicator;

import com.sun.health.dto.BaseDTO;

import java.util.List;

public class IndicatorRespDTO<T> extends BaseDTO {

    private  String indicator;

    private List<T> list;

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
