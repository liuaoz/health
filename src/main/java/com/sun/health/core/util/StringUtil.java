package com.sun.health.core.util;

import com.sun.health.core.comm.DataHolder;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * string util
 *
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/22
 **/
public final class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty() || str.isBlank();
    }

    /**
     * contain chinese
     *
     * @param src the string to be checked
     * @return true: contain chinese
     */
    public static boolean isContainChinese(String src) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(src);
        return m.find();
    }

    public static boolean contain(String content, List<String> items) {
        if (Objects.isNull(content) || content.isEmpty()) {
            return false;
        }

        for (String item : items) {
            if (content.contains(item)) {
                return true;
            }
        }
        return false;
    }

    public static DataHolder<Boolean, String> getContainItem(String content, List<String> items) {
        if (Objects.isNull(content) || content.isEmpty()) {
            return new DataHolder<>(false, null);
        }

        for (String item : items) {
            if (content.contains(item)) {
                return new DataHolder<>(true, item);
            }
        }
        return new DataHolder<>(false, null);
    }

    public static String rand32Str() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 32);
    }
}
