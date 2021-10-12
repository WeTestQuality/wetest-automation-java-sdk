package com.cloudtestapi.slot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResignResult {
    @Expose
    public int status;

    @Expose
    @SerializedName("file_url")
    public String fileUrl;
}
