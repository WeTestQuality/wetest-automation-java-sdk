package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Script {
    @Expose
    @SerializedName("script_id")
    public int scriptId;

    @Expose
    public int size;

    @Expose
    @SerializedName("script_url")
    public String scriptUrl;
}
