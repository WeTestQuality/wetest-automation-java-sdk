package com.cloudtestapi.account.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cloud {
    @Expose
    @SerializedName("cloud_id")
    public int cloudId;

    @Expose
    @SerializedName("cloud_name")
    public String cloudName;

    @Expose
    @SerializedName("cloud_type")
    public int cloudType;

    @Expose
    @SerializedName("device_type")
    public int deviceType;
}
