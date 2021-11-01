package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WTUploadResult {

    @Expose
    @SerializedName("fid")
    public String fileHashId;

    @Expose
    @SerializedName("status")
    // 0-Uploading 1-Success 2-Fail
    public int status;
}
