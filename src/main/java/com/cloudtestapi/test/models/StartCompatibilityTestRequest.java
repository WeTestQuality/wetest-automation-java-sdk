package com.cloudtestapi.test.models;

import com.cloudtestapi.common.AbstractRequest;
import com.cloudtestapi.common.profile.HttpProfile;
import com.google.gson.Gson;
import java.util.HashMap;

public class StartCompatibilityTestRequest extends AbstractRequest {

    private CompatibilityTest test;

    public StartCompatibilityTestRequest() {
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/tests/compatibility");
    }

    public CompatibilityTest getTest() {
        return test;
    }

    public void setTest(CompatibilityTest test) {
        this.test = test;
    }

    @Override
    protected byte[] toBody() {
        Gson gson = new Gson();
        return gson.toJson(test).getBytes();
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {

    }
}
