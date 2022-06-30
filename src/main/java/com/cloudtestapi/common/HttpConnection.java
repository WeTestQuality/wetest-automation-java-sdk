package com.cloudtestapi.common;

import com.cloudtestapi.common.exception.CloudTestSDKException;
import okhttp3.*;

import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

public class HttpConnection {

    private OkHttpClient.Builder client;

    public HttpConnection(Integer connTimeout, Integer readTimeout, Integer writeTimeout) {
        this.client =  new OkHttpClient.Builder().
                connectTimeout(connTimeout, TimeUnit.SECONDS).
                readTimeout(readTimeout, TimeUnit.SECONDS).
                writeTimeout(writeTimeout, TimeUnit.SECONDS);
    }

    public void addInterceptors(Interceptor interceptor) {
        this.client.interceptors().add(interceptor);
    }

    public void setProxy(Proxy proxy) {
        this.client.proxy(proxy);
    }

    public void setAuthenticator(Authenticator authenticator) {
        this.client.authenticator(authenticator);
    }

    public Response doRequest(Request request) throws CloudTestSDKException {
        Response response = null;
        try {
            response = this.client.build().newCall(request).execute();
        } catch (IOException e) {
            throw new CloudTestSDKException(e.getClass().getName() + "-" + e.getMessage());
        }
        return response;
    }

    public Response getRequest(String url) throws CloudTestSDKException {
        Request request = null;
        try {
            request = new Request.Builder().url(url).get().build();
        } catch (IllegalArgumentException e) {
            throw new CloudTestSDKException(e.getClass().getName() + "-" + e.getMessage());
        }

        return this.doRequest(request);
    }

    public Response deleteRequest(String url) throws CloudTestSDKException {
        Request request = null;
        try {
            request = new Request.Builder().url(url).delete().build();
        } catch (IllegalArgumentException e) {
            throw new CloudTestSDKException(e.getClass().getName() + "-" + e.getMessage());
        }
        return this.doRequest(request);
    }

    public Response postRequest(String url, byte[] body) throws CloudTestSDKException {
        MediaType contentType = MediaType.parse("application/json");
        Request request = null;
        try {
            request = new Request.Builder().url(url).post(RequestBody.create(contentType, body)).build();
        } catch (IllegalArgumentException e) {
            throw new CloudTestSDKException(e.getClass().getName() + "-" + e.getMessage());
        }

        return this.doRequest(request);
    }

    public Response putRequest(String url, byte[] body) throws CloudTestSDKException {
        MediaType contentType = MediaType.parse("application/json");
        Request request = null;
        try {
            request = new Request.Builder().url(url).put(RequestBody.create(contentType, body)).build();
        } catch (IllegalArgumentException e) {
            throw new CloudTestSDKException(e.getClass().getName() + "-" + e.getMessage());
        }
        return this.doRequest(request);
    }

    public Response postFormRequest(String url, byte[] body, String fieldName,
            String fileName, String fileMime) throws CloudTestSDKException {
        Request request;
        try {

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MediaType.parse("multipart/form-data"))
                    .addFormDataPart(fieldName, fileName,
                            RequestBody.create(MediaType.parse(fileMime), body))
                    .build();
            request =
                    new Request.Builder()
                            .url(url)
                            .post(requestBody)
                            .build();
        } catch (IllegalArgumentException e) {
            throw new CloudTestSDKException(e.getClass().getName() + "-" + e.getMessage());
        }

        return this.doRequest(request);
    }

    public Response postRequest(String url, byte[] body, Headers headers)
            throws CloudTestSDKException {
        MediaType contentType = MediaType.parse(headers.get("Content-Type"));
        Request request = null;
        try {
            request =
                    new Request.Builder()
                            .url(url)
                            .post(RequestBody.create(contentType, body))
                            .headers(headers)
                            .build();
        } catch (IllegalArgumentException e) {
            throw new CloudTestSDKException(e.getClass().getName() + "-" + e.getMessage());
        }

        return this.doRequest(request);
    }


}
