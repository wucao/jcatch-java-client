package com.xxg.jcatch.client;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by wucao on 17/3/15.
 */
public class HttpUtil {

    /**
     *  post方式提交字节数组
     *  @param url URL
     *  @param byteArray 字节数组
     *  @param charset web编码方式
     *  @param contentType contextType
     *  @return web返回内容
     *  @throws IOException io异常
     *  @author XXG
     */
    public static String post(String url, byte[] byteArray, String charset, String contentType) throws IOException {
        OutputStream output = null;
        InputStream input = null;
        URLConnection connection = new URL(url).openConnection();
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);
        if(contentType != null) {
            connection.setRequestProperty("Content-Type", contentType);
        }
        try {
            connection.setDoOutput(true);
            output = connection.getOutputStream();
            output.write(byteArray);
            output.flush();
            input = connection.getInputStream();
            return IOUtils.toString(input, charset);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }
}
