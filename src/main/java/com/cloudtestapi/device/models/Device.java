package com.cloudtestapi.device.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device extends DeviceBasicInfo {

    @Expose
    @SerializedName("offline_reason")
    public String offlineReason;

    @Expose
    @SerializedName("test_queue")
    public int[] testQueue;

    @Expose
    @SerializedName("debug_url")
    public String debugUrl;
}
