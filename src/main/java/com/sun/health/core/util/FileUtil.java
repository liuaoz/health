package com.sun.health.core.util;

import java.io.*;

/**
 * file util
 */
public final class FileUtil {

    /**
     * 读取文件内容为二进制数组
     *
     * @param filePath file path
     * @return file content
     * @throws IOException ex
     */
    public static byte[] read(String filePath) throws IOException {

        InputStream in = new FileInputStream(filePath);
        byte[] data = inputStream2ByteArray(in);
        in.close();
        return data;
    }

    /**
     * 流转二进制数组
     *
     * @param in input stream
     * @return byte array
     * @throws IOException ex
     */
    public static byte[] inputStream2ByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    /**
     * 保存文件
     *
     * @param filePath file path
     * @param fileName file name
     * @param content  file content
     */
    public static void save(String filePath, String fileName, byte[] content) {
        try {
            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File file = new File(fileDir, fileName);
            OutputStream os = new FileOutputStream(file);
            os.write(content, 0, content.length);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
