package com.cloudtestapi.test.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.CommonTestRequest;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class CancelTestRequest extends CommonTestRequest {

    public CancelTestRequest() {
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/tests/:test_id/cancelation");
    }
}
