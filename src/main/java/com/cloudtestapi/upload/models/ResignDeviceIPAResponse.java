package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResignDeviceIPAResponse {
    @Expose
    @SerializedName("url")
    public String fileUrl;
}
