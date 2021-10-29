package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CaseConfig {

    @Expose
    public Case[] cases;

    @Expose
    @SerializedName("retry_times")
    public int retryTimes;

    @Expose
    @SerializedName("case_retry_mode")
    public String caseRetryMode;

    @Expose
    @SerializedName("max_case_runtime")
    public int maxCaseRunTime;

    @Expose
    @SerializedName("case_batch_number")
    public int caseBatchNumber;
}
