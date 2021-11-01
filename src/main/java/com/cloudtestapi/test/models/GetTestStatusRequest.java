package com.cloudtestapi.test.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class GetTestStatusRequest extends AbstractRequestWithoutSpecificBodyGenerator {

    private Long testId;

    public GetTestStatusRequest() {
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/tests/:test_id/status");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {
    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":test_id", testId.toString());
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
}
