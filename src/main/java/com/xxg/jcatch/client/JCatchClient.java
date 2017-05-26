package com.xxg.jcatch.client;

import org.json.JSONObject;

import java.io.*;
import java.net.URLEncoder;

/**
 * Created by wucao on 17/3/15.
 */
public class JCatchClient {

    private String baseUrl;
    private String appId;
    private String secretKey;

    public JCatchClient() {}

    public JCatchClient(String baseUrl, String appId, String secretKey) {
        this.baseUrl = baseUrl;
        this.appId = appId;
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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

    public void submit(Exception e)  {
        try {
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer, true));
            String stackTrace = writer.toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("stackTrace", stackTrace);
            jsonObject.put("exceptionName", e.getClass().getName());
            jsonObject.put("message", e.getMessage());
            if(e.getStackTrace() != null && e.getStackTrace().length > 0) {
                StackTraceElement element = e.getStackTrace()[0];
                jsonObject.put("className", element.getClassName());
                jsonObject.put("fileName", element.getFileName());
                jsonObject.put("methodName", element.getMethodName());
                jsonObject.put("lineNumber", element.getLineNumber());
            }

            String res = HttpUtil.post(baseUrl + "/api/submitExceptionJson?appId=" + URLEncoder.encode(appId, "UTF-8"),
                    jsonObject.toString().getBytes("UTF-8"), "UTF-8", "application/octet-stream");
            JSONObject response = new JSONObject(res);
            if (!response.getBoolean("success")) {
                throw new IOException("Submit failed: " + res);
            }
        } catch (Exception exception) {
            exception.getStackTrace();
        }
    }
}
