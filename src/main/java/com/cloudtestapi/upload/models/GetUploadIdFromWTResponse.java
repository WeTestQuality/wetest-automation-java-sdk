package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUploadIdFromWTResponse {

    @Expose
    @SerializedName("id")
    public String id;

    @Expose
    @SerializedName("chunk_size")
    public long chunkSize;

    @Expose
    @SerializedName("chunk_number")
    public int chunkNumber;
}
