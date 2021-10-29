package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DumpAppResponse {

    @Expose
    @SerializedName("app")
    public App app;
}
