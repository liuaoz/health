package com.sun.health.service.crawler.house;

import java.util.List;

public class HousingEstateResp {

    private Integer q;

    private String p;

    private List<HousingEstateDto> s;

    public Integer getQ() {
        return q;
    }

    public void setQ(Integer q) {
        this.q = q;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public List<HousingEstateDto> getS() {
        return s;
    }

    public void setS(List<HousingEstateDto> s) {
        this.s = s;
    }
}
