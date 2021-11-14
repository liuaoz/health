package com.sun.health.core.util;

import com.google.gson.Gson;

/**
 * json util
 *
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/22
 **/
public final class JsonUtil {

    public static <T> T fromJson(String src, Class<T> cls) {
        Gson gson = new Gson();
        return gson.fromJson(src, cls);
    }

    public static String toJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
