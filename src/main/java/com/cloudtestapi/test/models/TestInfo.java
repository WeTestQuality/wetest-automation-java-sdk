package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestInfo {
    @Expose
    @SerializedName("test_id")
    public Long testId;

    @Expose
    @SerializedName("report_url")
    public String reportUrl;
}
