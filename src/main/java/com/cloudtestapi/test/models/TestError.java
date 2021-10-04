package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestError {
    @Expose
    public String content;

    @Expose
    public String description;

    @Expose
    @SerializedName("error_time")
    public String errorTime;

    @Expose
    public String level;

    @Expose
    @SerializedName("error_reson")
    public String errorReason;
}
