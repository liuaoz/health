package com.sun.health.service.crawler;

public enum CrawlerSource {

    XIAO_QU_SHUO("xiaoqushuo"),
    AN_JU_KE("anjuke");

    private String name;

    CrawlerSource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
