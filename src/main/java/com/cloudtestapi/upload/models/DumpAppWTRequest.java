package com.cloudtestapi.upload.models;

import com.cloudtestapi.common.AbstractUploadRequest;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class DumpAppWTRequest extends AbstractUploadRequest {

    private byte[] body;

    private String uploadId;

    public DumpAppWTRequest() {
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/platform/upload/dump");
        this.setFieldName("data");
        this.setFileMime("application/zip");
    }

    @Override
    protected byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {
        map.put("id", uploadId);
    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {

    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }
}
