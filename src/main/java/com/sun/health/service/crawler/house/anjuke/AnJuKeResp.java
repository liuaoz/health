package com.sun.health.service.crawler.house.anjuke;

import java.util.List;

public class AnJuKeResp {

    private String status;

    private List<AnJuKeDto> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AnJuKeDto> getData() {
        return data;
    }

    public void setData(List<AnJuKeDto> data) {
        this.data = data;
    }
}
