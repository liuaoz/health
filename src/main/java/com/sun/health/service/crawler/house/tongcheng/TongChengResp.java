package com.sun.health.service.crawler.house.tongcheng;

import java.util.List;

public class TongChengResp {

    private String status;

    private List<TongChengDto> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TongChengDto> getData() {
        return data;
    }

    public void setData(List<TongChengDto> data) {
        this.data = data;
    }
}
