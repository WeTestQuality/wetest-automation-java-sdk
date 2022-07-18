package com.cloudtestapi.common;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

class TCLog implements Interceptor {

    private boolean debug;
    private Log logger = null;
    private Gson gson = null;

    public TCLog(String name, boolean isDebug) {
        logger = LogFactory.getLog(name);
        this.debug = isDebug;
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public TCLog(Log logger, boolean isDebug) {
        this.logger = logger;
        this.debug = isDebug;
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public Log getLogger() {
        return this.logger;
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }

    public void info(final String str) {
        logger.info(str);
    }

    public void info(final String str, final Throwable t) {
        logger.info(str, t);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (debug) {
            this.logger.info("send request, request url: " + request.urlString());
            this.logger.info("request header: " + request.headers().toString());
            this.logger.info("request body: " + gson.toJson(request.body()));
        }

        Response response = chain.proceed(request);

        if (debug) {
            this.logger.info("response header:" + response.headers().toString());
            this.logger.info("response body:" + response.body().string());
        }

        return response;
    }

}
