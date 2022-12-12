package com.cloudtestapi.slot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelInfo {
    @SerializedName("model_id")
    @Expose
    public int modelId;

    @Expose
    @SerializedName("model_name")
    public String model;

    @Expose
    @SerializedName("model_alias")
    public String modelAlias;

    @Expose
    public String manu;

    @Expose
    public String version;

    @Expose
    public String resolution;

    @Expose
    @SerializedName("phone_type_code")
    public int phoneTypeCode;

    @Expose
    @SerializedName("phone_type")
    public String phoneType;

    @Expose
    @SerializedName("cpu_arch")
    public String cpuArch;

    @Expose
    @SerializedName("cpu_total")
    public int cpuTotal;

    @Expose
    @SerializedName("ram_total")
    public int ramTotal;

    @Expose
    @SerializedName("rom_total")
    public int romTotal;

    @Expose
    @SerializedName("form_factor_type")
    public int formFactorType;

    @Expose
    @SerializedName("device_id")
    public int deviceId;

    @Expose
    @SerializedName("device_name")
    public String deviceName;
}
