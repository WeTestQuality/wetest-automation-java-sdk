package com.cloudtestapi.device.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelListInfoBase {

    @Expose
    @SerializedName("model")
    public String model;

    @Expose
    @SerializedName("version")
    public String version;

    @Expose
    @SerializedName("resolution")
    public String resolution;

    @Expose
    @SerializedName("cpu_name")
    public String cpuName;

    @Expose
    @SerializedName("cpu_arc")
    public String cpuArc;

    @Expose
    @SerializedName("opengl")
    public String opengl;

    @Expose
    @SerializedName("cpu_total")
    public Integer cpuTotal;

    @Expose
    @SerializedName("cpu_ghz")
    public Float cpuGhz;

}
