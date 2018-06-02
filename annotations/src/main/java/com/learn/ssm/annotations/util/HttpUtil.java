package com.learn.ssm.annotations.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * POST 请求
     * @param url 请求地址
     * @param params 请求参数
     * @return
     * @throws IOException
     */
    public static String sendRequest(String url, String params){
        String msg = "";
        URL requestUrl;
        HttpURLConnection connection = null;
        OutputStream out = null;
        BufferedReader reader = null;
        try {
            requestUrl  = new URL(url);
            //创建HttpURLConnection对象
            connection = (HttpURLConnection) requestUrl.openConnection();
            /*设置请求参数*/

            //设置请求方式
            connection.setRequestMethod("POST");
            //设置请求头
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            //设置超时时间 底层通过socket通信实现。如果不设置超时（timeout），在网络异常的情况下，可能会导致程序僵死而不继续往下执行。
            connection.setConnectTimeout(3000);
            //设置输出
            connection.setDoOutput(true);
            //设置输入
            connection.setDoInput(true);
            // 设置是否使用缓存
            connection.setUseCaches(false);
            // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            connection.setInstanceFollowRedirects(true);

            //设置请求内容
            out = connection.getOutputStream();
            out.write(params.getBytes());
            out.flush();
            out.close();

            //获取响应信息
            int code = connection.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK){
                //请求成功
                reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    msg += line + "\n";
                }
                reader.close();
                //关闭连接
                connection.disconnect();
            }
        } catch (Exception e) {
            log.error("发送POST请求失败: " + e.getMessage());

        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    reader = null;
                }
            }

            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                   e.getStackTrace();
                }
            }

            if (connection != null){
                connection.disconnect();
            }
        }
        return msg;
    }

    /**
     * GET 请求
     * @param url 请求地址
     * @return
     */
    public String sendRequest(String url){
        URL requestUrl;
        String msg = "";
        HttpURLConnection connection = null;
        BufferedReader in = null;
        try {
            requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();
            /*设置请求方式*/
            connection.setRequestMethod("GET");
            /*设置请求头*/
            connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            /*连接*/
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line = in.readLine();
            while (line != null) {
                msg += line;
                line = in.readLine();
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                connection.disconnect();
            }
        }
        return msg;
    }
}
