package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestDevice {

    @Expose
    @SerializedName("device_id")
    public int deviceID;

    @Expose
    @SerializedName("test_id")
    public Long testId;

    @Expose
    @SerializedName("start_time")
    public Long startTime;

    @Expose
    @SerializedName("end_time")
    public Long endTime;

    @Expose
    @SerializedName("result_code")
    public int resultCode;

    @Expose
    public String result;

    @Expose
    public String model;

    @Expose
    public String version;

    @Expose
    @SerializedName("mobile_log_url")
    public String mobileLogUrl;

    @Expose
    @SerializedName("app_log_url")
    public String appLogUrl;

    @Expose
    @SerializedName("script_log_url")
    public String scriptLogUrl;

    @Expose
    public TestError[] errors;

    @Expose
    public TestImage[] images;
}
