package com.cloudtestapi.upload.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class DumpScriptRequest extends AbstractRequestWithoutSpecificBodyGenerator {

    private String uploadId;

    public DumpScriptRequest() {
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/files/:upload_id/script");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":upload_id", uploadId);
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }
}
