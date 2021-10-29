package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class App {

    @Expose
    @SerializedName("app_id")
    public int appId;

    @Expose
    @SerializedName("app_type")
    public String appType;

    @Expose
    public int size;

    @Expose
    public String version;

    @Expose
    public String name;

    @Expose
    @SerializedName("package")
    public String appPackage;

    @Expose
    @SerializedName("app_url")
    public String appUrl;
}
