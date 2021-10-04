package com.cloudtestapi.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonResponseErrModel {

    @SerializedName("ret")
    @Expose
    public Integer code;

    @SerializedName("msg")
    @Expose
    public String msg;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("request_id")
    @Expose
    public String traceId;
}