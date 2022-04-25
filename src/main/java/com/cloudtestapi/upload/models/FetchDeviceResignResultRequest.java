package com.cloudtestapi.upload.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class FetchDeviceResignResultRequest extends AbstractRequestWithoutSpecificBodyGenerator {
    @SerializedName("device_id")
    @Expose
    private Integer deviceId;

    @SerializedName("file_id")
    @Expose
    private String fileId;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public FetchDeviceResignResultRequest(){
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/devices/:device_id/resign/status");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":device_id", this.deviceId.toString());
    }



}
