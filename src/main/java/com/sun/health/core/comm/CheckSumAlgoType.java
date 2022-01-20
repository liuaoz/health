package com.sun.health.core.comm;

public enum CheckSumAlgoType {

    MD5("MD5"), SHA_256("SHA-256"), SHA_512("SHA-512"), SHA_1("SHA1");
    private final String name;

    CheckSumAlgoType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
