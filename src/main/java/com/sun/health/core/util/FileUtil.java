package com.sun.health.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * file util
 */
public final class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

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

    public static byte[] toByteArrayByNio(File file) throws IOException {

        try (FileInputStream fs = new FileInputStream(file); FileChannel channel = fs.getChannel()) {

            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
            }
            return byteBuffer.array();
        }
    }

    public static byte[] toByteArray1(File file) throws IOException {
        int length = (int) file.length();
        byte[] data = new byte[length];
        int num = new FileInputStream(file).read(data);
        logger.info("total number is {}", num);
        return data;
    }

    public static byte[] toByteArray2(File file) throws IOException {

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        }
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
                boolean r = fileDir.mkdirs();
                if (!r) {
                    logger.warn("create directory failed. filePath={}", filePath);
                }
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
