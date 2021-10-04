package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAppInfoResponse {
    @Expose
    @SerializedName("app")
    public App app;
}
