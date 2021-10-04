package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Case {
    @Expose
    @SerializedName("args")
    public String[] args;

    @Expose
    @SerializedName("case_num")
    public String caseName;

    @Expose
    public String file;

    @Expose
    public String func;

    @Expose
    public String[] labels;

    @Expose
    @SerializedName("retry_times")
    public int retryTimes;

    @Expose
    @SerializedName("max_case_runtime")
    public int maxCaseRunTime;
}
