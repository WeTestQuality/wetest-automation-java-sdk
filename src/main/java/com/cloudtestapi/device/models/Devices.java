package com.cloudtestapi.device.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Devices {

    @Expose
    public Device[] devices;

    @Expose
    @SerializedName("offline_devices")
    public Device[] offlineDevices;
}
