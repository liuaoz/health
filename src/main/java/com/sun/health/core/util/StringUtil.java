package com.sun.health.core.util;

import com.sun.health.core.comm.DataHolder;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;
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

    /**
     * 生成指定范围的数[min,max]
     */
    public static int randInt(int min, int max) {
        return new Random().nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 生成字符串（包含number个字母(a-z)）
     */
    public static String randLetter(int number) {

        int start = 'a';
        int end = 'z';

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < number; i++) {
            int tmp = random.nextInt(end) % (end - start + 1) + start;
            sb.append((char) tmp);
        }
        return sb.toString();
    }

    public static String randHanzi() {

        int highPos, lowPos; // 定义高低位

        Random random = new Random();

        highPos = (176 + Math.abs(random.nextInt(39)));//获取高位值

        lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值

        byte[] b = new byte[2];

        b[0] = (Integer.valueOf(highPos).byteValue());

        b[1] = (Integer.valueOf(lowPos).byteValue());

        try {
            return new String(b, "GBK");//转成中文
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     */
    public static List<String> sub(String str, int count) {

        if (Objects.isNull(str) || str.length() < count) {
            return new ArrayList<>();
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < str.length() - count + 1; i++) {
            list.add(str.substring(i, i + count));
        }
        return list;
    }

    public static void main(String[] args) {

        List<String> list = sub("上大阳光乾静园", 2);

        System.out.println(list);
    }

}
