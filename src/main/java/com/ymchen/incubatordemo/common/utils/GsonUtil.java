package com.ymchen.incubatordemo.common.utils;

import com.google.gson.Gson;

public class GsonUtil {
    private final static GsonUtil INSTANCE = new GsonUtil();

    private static final Gson GSON = new Gson();

    private GsonUtil() {

    }

    public static GsonUtil getInstance() {
        return INSTANCE;
    }


    public String toJson(final Object object) {
        return GSON.toJson(object);
    }


    public <T> T fromJson(final String json, final Class<T> tClass) {
        return GSON.fromJson(json, tClass);
    }

}
