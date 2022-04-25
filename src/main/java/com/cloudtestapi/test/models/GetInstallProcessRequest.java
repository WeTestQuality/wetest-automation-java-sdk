package com.cloudtestapi.test.models;

import com.cloudtestapi.common.CommonTestDeviceRequest;
import com.cloudtestapi.common.profile.HttpProfile;

import java.util.HashMap;

public class GetInstallProcessRequest extends CommonTestDeviceRequest {
    private Integer installId;

    public Integer getInstallId() {
        return installId;
    }

    public void setInstallId(Integer installId) {
        this.installId = installId;
    }

    public GetInstallProcessRequest() {
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/tests/:test_id/devices/:device_id/install/:install_id/process");
    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        super.toPathParamMap(map, prefix);
        map.put(":install_id", installId.toString());
    }
}
