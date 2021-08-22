package com.sun.health.core.util;

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

}
