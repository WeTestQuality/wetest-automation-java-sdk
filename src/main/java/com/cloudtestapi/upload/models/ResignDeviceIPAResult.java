package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResignDeviceIPAResult {
    @Expose
    public int status;

    @Expose
    @SerializedName("file_url")
    public String fileUrl;
}
