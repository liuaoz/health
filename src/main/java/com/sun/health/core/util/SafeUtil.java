package com.sun.health.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Matrix
 * @version v1.0.0
 * @since : 2021/8/21
 **/
public final class SafeUtil {

    private static final Logger logger = LoggerFactory.getLogger(SafeUtil.class);
    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    public static final String ALGORITHM_MD5 = "MD5";
    public static final String ALGORITHM_HMACSHA256 = "HmacSHA256";

    public static String base64Encode(byte[] src) {
        return Base64.getEncoder().encodeToString(src);
    }

    public static byte[] base64Decode(String src) {
        return Base64.getDecoder().decode(src);
    }

    public static String md5(String src) {
        try {
            MessageDigest instance = MessageDigest.getInstance(ALGORITHM_MD5);
            byte[] digest = instance.digest(src.getBytes(StandardCharsets.UTF_8));
            return new String(encodeHex(digest));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(ALGORITHM_MD5 + "算法不存在");
        }
    }

    public static String hmacSHA256(String src, String key) {
        try {
            Mac instance = Mac.getInstance(ALGORITHM_HMACSHA256);
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM_HMACSHA256);
            instance.init(secretKey);
            byte[] digest = instance.doFinal(src.getBytes(StandardCharsets.UTF_8));
            return new String(encodeHex(digest));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(ALGORITHM_MD5 + "算法不存在");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("invalid key:" + key);
        }
    }


    private static char[] encodeHex(byte[] bytes) {
        char[] chars = new char[32];
        for (int i = 0; i < chars.length; i += 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[b >>> 4 & 15];
            chars[i + 1] = HEX_CHARS[b & 15];
        }
        return chars;
    }

    public static String genChecksum(File file, String checkSumAlgo) throws NoSuchAlgorithmException, IOException {
        MessageDigest messageDigest = MessageDigest.getInstance(checkSumAlgo);
        messageDigest.update(Files.readAllBytes(file.toPath()));
        byte[] digestBytes = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digestBytes) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String hash(byte[] content, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        byte[] digest = messageDigest.digest(content);
        return new String(encodeHex(digest));
    }
}
