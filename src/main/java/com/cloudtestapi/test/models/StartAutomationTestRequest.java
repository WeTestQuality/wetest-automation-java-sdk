package com.cloudtestapi.test.models;

import com.cloudtestapi.common.AbstractRequest;
import com.cloudtestapi.common.profile.HttpProfile;
import com.google.gson.Gson;
import java.util.HashMap;

public class StartAutomationTestRequest extends AbstractRequest {
    private AutomationTest test;

    public StartAutomationTestRequest(){
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/tests/automation");
    }

    @Override
    protected String toJsonBody() {
        Gson gson = new Gson();
        return gson.toJson(test);
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {

    }

    public AutomationTest getTest() {
        return test;
    }

    public void setTest(AutomationTest test) {
        this.test = test;
    }
}
