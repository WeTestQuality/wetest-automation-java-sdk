package com.cloudtestapi.device.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDeviceStateResponse {

    @Expose
    @SerializedName("device_info")
    public DeviceBasicInfo deviceInfo;
}
