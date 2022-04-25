package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DebugTestInfo {
    @Expose
    @SerializedName("test_id")
    public Long testId;

    @Expose
    @SerializedName("control_key")
    public String controlKey;

    @Expose
    @SerializedName("start_time")
    public String startTime;

    @Expose
    @SerializedName("token")
    public String token;

    @Expose
    @SerializedName("address")
    public String address;


}
