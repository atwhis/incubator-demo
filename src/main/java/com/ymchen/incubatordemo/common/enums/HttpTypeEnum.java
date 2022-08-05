package com.ymchen.incubatordemo.common.enums;

public enum HttpTypeEnum {
    POST("post"),
    GET("get");

    HttpTypeEnum(String name) {
        this.name = name;
    }

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
