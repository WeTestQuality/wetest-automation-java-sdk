package com.cloudtestapi.device.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// ModelList 机型列表
public class ModelList {
    @Expose
    public int id;

    @Expose
    public String name;

    @Expose
    @SerializedName("model_ids")
    public int[] modelIds;

    @Expose
    @SerializedName("cloud_id")
    public int cloudId;

    @Expose
    @SerializedName("cloud_name")
    public String cloudName;

    @Expose
    @SerializedName("device_type")
    public int deviceType;

    @Expose
    @SerializedName("project_id")
    public String projectId;

    @Expose
    @SerializedName("project_name")
    public String projectName;

    @Expose
    @SerializedName("filter_type")
    public int filterType;

    @Expose
    @SerializedName("device_ids")
    public int[] deviceIds;
}
