package com.sun.health.core.util;

public final class NumberUtil {

    /**
     * 判断两个值是否是近似值。条件为差值小于6
     */
    public static boolean isSimilar(Number a, Number b) {
        return Math.abs(a.doubleValue() - b.doubleValue()) < 6;
    }

    /**
     * 判断两个值是否是近似值。条件为差值小于delta
     */
    public static boolean isSimilar(Number a, Number b, Number delta) {
        return Math.abs(a.doubleValue() - b.doubleValue()) < delta.doubleValue();
    }

    /**
     * 判断一个字符是否为数字
     */
    public static boolean isNumeric(char c) {
        return Character.isDigit(c);
    }

    /**
     * 判断一个字符串是否为数值
     */
    public static boolean isNumeric(String str) {

        return false;
    }
}
