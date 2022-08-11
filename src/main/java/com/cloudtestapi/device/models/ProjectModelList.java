package com.cloudtestapi.device.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectModelList {

    @Expose
    @SerializedName("id")
    public Integer id;

    @Expose
    @SerializedName("cloud_id")
    public Integer cloudId;

    @Expose
    @SerializedName("cloud_name")
    public String cloudName;

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("user")
    public String user;

    @Expose
    @SerializedName("model_count")
    public Integer modelCount;

    @Expose
    @SerializedName("filter_type")
    public Integer filterType;

    @Expose
    @SerializedName("device_type")
    public Integer deviceType;
}
