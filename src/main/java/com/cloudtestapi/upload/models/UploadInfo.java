package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadInfo {

    @Expose
    @SerializedName("chunk_number")
    public int chunkNumber;

    @Expose
    @SerializedName("chunk_size")
    public long chunkSize;

    @Expose
    @SerializedName("upload_id")
    public String uploadId;


    public String fileName;

    public String fileType;

    public long fileSize;
}
