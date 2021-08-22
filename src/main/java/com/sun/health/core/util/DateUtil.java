package com.sun.health.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * date util
 */
public final class DateUtil {

    public static final String PATTERN_YYYYMMDD="yyyyMMdd";

    /**
     * yyyyMMdd -> date
     */
    public static Date fromYyyyMMdd(String str){
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YYYYMMDD);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("invalid date str");
    }

}
