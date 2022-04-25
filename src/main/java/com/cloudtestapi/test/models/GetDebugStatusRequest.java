package com.cloudtestapi.test.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.CommonTestDeviceRequest;
import com.cloudtestapi.common.profile.HttpProfile;

import java.util.HashMap;

public class GetDebugStatusRequest extends CommonTestDeviceRequest {

    public GetDebugStatusRequest() {
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/tests/:test_id/devices/:device_id/debug/status");
    }
}
