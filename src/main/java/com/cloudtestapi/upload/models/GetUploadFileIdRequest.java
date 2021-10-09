package com.cloudtestapi.upload.models;

import com.cloudtestapi.common.AbstractRequestWithoutBodyAdapter;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class GetUploadFileIdRequest extends AbstractRequestWithoutBodyAdapter {
    private long fileSize;

    private String fileName;

    private String uploadFileType;

    public GetUploadFileIdRequest(){
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/files");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {
        map.put("size", fileSize);
        map.put("file_name", fileName);
        map.put("type", uploadFileType);
    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {

    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadFileType() {
        return uploadFileType;
    }

    public void setUploadFileType(String uploadFileType) {
        this.uploadFileType = uploadFileType;
    }
}
