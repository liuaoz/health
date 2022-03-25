package com.sun.health.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * http util
 */
public final class HttpUtil {

    public static void main(String[] args) {
        ipProxy("122.234.168.113","8060");
    }

    public static void ipProxy(String host, String port) {

        SocketAddress addr = new InetSocketAddress(host, Integer.parseInt(port));

        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);

        try {

//            URL url = new URL("http://www.xiaoqushuo.com/getyt?kw=ala&cb=sh&callback=sh&_=1647702066917");
            URL url = new URL("http://www.baidu.com");

            URLConnection conn = url.openConnection(proxy);

            conn.setConnectTimeout(5000);

//            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; NT 5.1; GTB5; .NET CLR 2.0.50727; CIBA)");

            InputStream is = conn.getInputStream();

            byte[] bytes = is.readAllBytes();

            System.out.println(new String(bytes));

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    /**
     * 发送post请求，根据 Content-Type 返回不同的返回值
     *
     * @param url
     * @param header
     * @param body
     * @return
     */
    public static Map<String, Object> doPost2(String url, Map<String, String> header, String body) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PrintWriter out = null;
        try {
            // 设置 url
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            // 设置 header
            for (String key : header.keySet()) {
                httpURLConnection.setRequestProperty(key, header.get(key));
            }
            // 设置请求 body
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            out = new PrintWriter(httpURLConnection.getOutputStream());
            // 保存body
            out.print(body);
            // 发送body
            out.flush();
            if (HttpURLConnection.HTTP_OK != httpURLConnection.getResponseCode()) {
                System.out.println("Http 请求失败，状态码：" + httpURLConnection.getResponseCode());
                return null;
            }
            // 获取响应header
            String responseContentType = httpURLConnection.getHeaderField("Content-Type");
            if ("audio/mpeg".equals(responseContentType)) {
                // 获取响应body
                byte[] bytes = FileUtil.inputStream2ByteArray(httpURLConnection.getInputStream());
                resultMap.put("Content-Type", "audio/mpeg");
                resultMap.put("sid", httpURLConnection.getHeaderField("sid"));
                resultMap.put("body", bytes);
                return resultMap;
            } else {
                // 设置请求 body
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String line;
                String result = "";
                while ((line = in.readLine()) != null) {
                    result += line;
                }
                resultMap.put("Content-Type", "text/plain");
                resultMap.put("body", result);

                return resultMap;
            }
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 发送post请求
     *
     * @param url
     * @param header
     * @param body
     * @return
     */
    public static String doPost1(String url, Map<String, String> header, String body) {
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            // 设置 url
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            // 设置 header
            for (String key : header.keySet()) {
                httpURLConnection.setRequestProperty(key, header.get(key));
            }
            // 设置请求 body
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            out = new PrintWriter(httpURLConnection.getOutputStream());
            // 保存body
            out.print(body);
            // 发送body
            out.flush();
            if (HttpURLConnection.HTTP_OK != httpURLConnection.getResponseCode()) {
                System.out.println("Http 请求失败，状态码：" + httpURLConnection.getResponseCode());
                return null;
            }

            // 获取响应body
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            return null;
        }
        return result;
    }
}
