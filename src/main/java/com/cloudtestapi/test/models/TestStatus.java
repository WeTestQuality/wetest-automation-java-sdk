package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestStatus {
    @Expose
    @SerializedName("start_time")
    public String startTime;

    @Expose
    @SerializedName("end_time")
    public String endTime;

    @Expose
    public boolean finished;

}
