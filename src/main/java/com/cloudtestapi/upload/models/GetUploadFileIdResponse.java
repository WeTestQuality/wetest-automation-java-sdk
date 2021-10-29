package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUploadFileIdResponse {

    @SerializedName("upload_info")
    @Expose
    public UploadInfo uploadInfo;
}
