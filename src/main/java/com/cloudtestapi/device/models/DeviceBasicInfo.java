package com.cloudtestapi.device.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceBasicInfo {
    @Expose
    @SerializedName("cloud_id")
    public int cloudID;

    @Expose
    @SerializedName("model_id")
    public int modelID;

    @Expose
    @SerializedName("device_id")
    public int deviceID;

    @Expose
    @SerializedName("test_id")
    public Long testID;

    @Expose
    public String manufacture;

    @Expose
    public String model;

    @Expose
    @SerializedName("model_alias")
    public String modelAlias;

    @Expose
    public String version;

    @Expose
    @SerializedName("cpu_total")
    public String cpuTotal;

    @Expose
    public String location;

    @Expose
    public String resolution;

    @Expose
    public int ram;

    @Expose
    @SerializedName("device_state")
    public String deviceState;

    @Expose
    @SerializedName("device_group")
    public String deviceGroup;

    @Expose
    @SerializedName("cpu_name")
    public String cpuName;

    @Expose
    @SerializedName("device_state_code")
    public int deviceStateCode;
}
