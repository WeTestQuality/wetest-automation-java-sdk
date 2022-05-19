package com.cloudtestapi.test.models;

import com.cloudtestapi.common.CommonTestDeviceRequest;
import com.cloudtestapi.common.profile.HttpProfile;

public class StartDebugConnectRequest extends CommonTestDeviceRequest {
    public StartDebugConnectRequest() {
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/tests/:test_id/devices/:device_id/debug/connect");
    }
}
