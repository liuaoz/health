package com.sun.health.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * date util
 */
public final class DateUtil {

    public static final String PATTERN_YYYYMMDD = "yyyyMMdd";
    public static final String PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static void main(String[] args) {
        String s = getStringTime(new Date());
        System.out.println(s);
    }

    /**
     * 包含日期时间的字符串
     *
     * @return e.g. 20211225061119
     */
    public static String getStringTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yyyyMMddHHmmss);

        return sdf.format(date);
    }

    /**
     * yyyyMMdd -> date
     */
    public static Date fromYyyyMMdd(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YYYYMMDD);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("invalid date str");
    }

}
