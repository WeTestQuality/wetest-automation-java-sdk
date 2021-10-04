package com.cloudtestapi.common;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

public class HttpConnection {

    private OkHttpClient client;

    public HttpConnection(Integer connTimeout, Integer readTimeout, Integer writeTimeout) {
        this.client = new OkHttpClient();
        this.client.setConnectTimeout(connTimeout, TimeUnit.SECONDS);
        this.client.setReadTimeout(readTimeout, TimeUnit.SECONDS);
        this.client.setWriteTimeout(writeTimeout, TimeUnit.SECONDS);
    }

    public void addInterceptors(Interceptor interceptor) {
        this.client.interceptors().add(interceptor);
    }

    public void setProxy(Proxy proxy) {
        this.client.setProxy(proxy);
    }

    public void setAuthenticator(Authenticator authenticator) {
        this.client.setAuthenticator(authenticator);
    }

    public Response doRequest(Request request) throws CloudTestSDKException {
        Response response = null;
        try {
            response = this.client.newCall(request).execute();
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

    public Response postRequest(String url, String body) throws CloudTestSDKException {
        MediaType contentType = MediaType.parse("application/json");
        Request request = null;
        try {
            request = new Request.Builder().url(url).post(RequestBody.create(contentType, body)).build();
        } catch (IllegalArgumentException e) {
            throw new CloudTestSDKException(e.getClass().getName() + "-" + e.getMessage());
        }

        return this.doRequest(request);
    }

    public Response putRequest(String url, String body) throws CloudTestSDKException {
        MediaType contentType = MediaType.parse("application/json");
        Request request = null;
        try {
            request = new Request.Builder().url(url).put(RequestBody.create(contentType, body)).build();
        } catch (IllegalArgumentException e) {
            throw new CloudTestSDKException(e.getClass().getName() + "-" + e.getMessage());
        }
        return this.doRequest(request);
    }

    public Response postRequest(String url, String body, Headers headers)
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
