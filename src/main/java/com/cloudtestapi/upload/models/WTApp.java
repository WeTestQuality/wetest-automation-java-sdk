package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WTApp {

    @Expose
    @SerializedName("app_hash_id")
    public String appHashId;

    @Expose
    public String version;

    @Expose
    public String name;

    @Expose
    @SerializedName("name_en")
    public String nameEn;

    @Expose
    @SerializedName("sdkVersion")
    public String sdkVersion;

    @Expose
    @SerializedName("targetSdkVersion")
    public String targetSdkVersion;

    @Expose
    @SerializedName("pkg")
    public String pkg;

    @Expose
    @SerializedName("icon")
    public String[] icon;
}
