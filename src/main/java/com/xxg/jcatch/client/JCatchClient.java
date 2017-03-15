package com.xxg.jcatch.client;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;

/**
 * Created by wucao on 17/3/15.
 */
public class JCatchClient {

    private String baseUrl;
    private String appId;

    public JCatchClient() {}

    public JCatchClient(String baseUrl, String appId) {
        this.baseUrl = baseUrl;
        this.appId = appId;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void submit(Exception e) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(e);
        IOUtils.closeQuietly(objectOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String res = HttpUtil.post(baseUrl + "/api/submitException?appId=" + URLEncoder.encode(appId, "UTF-8"), bytes, "UTF-8", "application/octet-stream");
        JSONObject jsonObject = new JSONObject(res);
        if(!jsonObject.getBoolean("success")) {
            throw new IOException("Submit failed: " + res);
        }
    }

    public void submitQuietly(Exception e) {
        try {
            submit(e);
        } catch (Exception exception) {
            exception.getStackTrace();
        }
    }
}
