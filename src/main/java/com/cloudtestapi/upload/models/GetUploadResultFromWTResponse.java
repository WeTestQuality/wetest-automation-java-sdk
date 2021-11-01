package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUploadResultFromWTResponse {

    @Expose
    public int ret;

    @Expose
    @SerializedName("data")
    public WTUploadResult uploadResult;
}
