package com.sun.health.comm;

public enum ZhangZhouReportItemTitle {

    no("NO"),
    item("项目"),
    result("结果"),
    reference("参考范围"),
    unit("单位"),
    inspectionMethod("检测方法");

    private final String name;

    ZhangZhouReportItemTitle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
