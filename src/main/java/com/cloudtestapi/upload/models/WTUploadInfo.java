package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;

public class WTUploadInfo {

    @Expose
    public String uploadId;

    @Expose
    public String path;

    @Expose
    public String md5;

    @Expose
    public long size;

    @Expose
    public String name;

    @Expose
    public int chunkNum;

    @Expose
    public long chunkSize;
}
