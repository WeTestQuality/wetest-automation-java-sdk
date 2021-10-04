package com.cloudtestapi.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class TCLog implements Interceptor {

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    private boolean debug;
    private Log logger = null;
    private Gson gson = null;

    public TCLog(String name, boolean isDebug) {
        logger = LogFactory.getLog(name);
        this.debug = isDebug;
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public void info(final String str) {
        if (debug) {
            logger.info(str);
        }
    }

    public void info(final String str, final Throwable t) {
        if (debug) {
            logger.info(str, t);
        }
    }



    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (debug){
            this.info("send request, request url: " + request.urlString());
            this.info("request header: " + request.headers().toString());
            this.info("request body: " + gson.toJson(request.body()));
        }

        Response response = chain.proceed(request);

        if (debug){
            this.info("response header:" + response.headers().toString());
            this.info("response body:" + response.body().string());
        }

        return response;
    }

}
