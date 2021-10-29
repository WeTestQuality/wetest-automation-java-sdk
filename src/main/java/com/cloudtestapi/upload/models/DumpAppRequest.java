package com.cloudtestapi.upload.models;

import com.cloudtestapi.common.AbstractUploadRequest;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class DumpAppRequest extends AbstractUploadRequest {

    private byte[] body;

    private String uploadId;

    public DumpAppRequest() {
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/files/:upload_id/app");
        this.setFieldName("file");
        this.setFileMime("application/zip");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":upload_id", uploadId);
    }

    @Override
    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }
}
