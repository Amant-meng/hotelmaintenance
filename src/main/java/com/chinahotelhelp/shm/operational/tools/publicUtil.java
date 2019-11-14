package com.chinahotelhelp.shm.operational.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName publicUtil
 * @Description TODO
 * @Author zhangjinyi
 * @Date 2019-06-27 15:18
 * @Version 1.0
 **/
@Slf4j
@Component
public class publicUtil {

    public static String DateToString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * <p>将文件转成base64 字符串</p>
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String path) {
        File file = new File(path);
        FileInputStream inputFile = null;
        try {
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return new BASE64Encoder().encode(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将base64 转为字节
     * @param base64
     * @return
     */
    public static byte[] encodeBase64Byte(String base64){
        BASE64Decoder decode = new BASE64Decoder();
        //将base64转换为byte[]
        byte[] b = new byte[0];
        try {
            b = decode.decodeBuffer(base64);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static byte[] image2Bytes(String imgSrc)
    {
        FileInputStream fin;
        byte[] bytes = null;
        try {
            fin = new FileInputStream(new File(imgSrc));
            bytes  = new byte[fin.available()];
            //将文件内容写入字节数组
            fin.read(bytes);
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bytes;
    }

    /**
     * 发送get请求
     * @param url
     * @return
     */
    public static String sendGetReq(String url) {
        log.info("sendGetReq Request：" + url);
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            java.util.Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("发送GET请求出现异常：" + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                log.error("in.close()出现异常：" + e2.getMessage());
            }
        }
        return result;
    }
}
