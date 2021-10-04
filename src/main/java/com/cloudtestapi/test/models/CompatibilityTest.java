package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompatibilityTest extends TestParam {
    @Expose
    @SerializedName("max_device_runtime")
    public int maxDeviceRunTime;

    public int getMaxDeviceRunTime() {
        return maxDeviceRunTime;
    }

    public void setMaxDeviceRunTime(int maxDeviceRunTime) {
        this.maxDeviceRunTime = maxDeviceRunTime;
    }

}
