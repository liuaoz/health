package com.sun.health.core.util;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

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

    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> List<T> json2List(String src, Class<T> cls) {
        Type type = new ParameterizedTypeImpl<>(cls);
        return new Gson().fromJson(src, type);
    }


    private static class ParameterizedTypeImpl<T> implements ParameterizedType {
        Class<T> clazz;

        public ParameterizedTypeImpl(Class<T> clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }


}
