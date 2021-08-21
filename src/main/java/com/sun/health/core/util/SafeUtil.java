package com.sun.health.core.util;

import java.util.Base64;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/21
 **/
public final class SafeUtil {

    public static String base64Encode(byte[] src) {
        return Base64.getEncoder().encodeToString(src);
    }

    public static byte[] base64Decode(String src) {
        return Base64.getDecoder().decode(src);
    }
}
