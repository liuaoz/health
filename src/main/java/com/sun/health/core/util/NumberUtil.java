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
}
