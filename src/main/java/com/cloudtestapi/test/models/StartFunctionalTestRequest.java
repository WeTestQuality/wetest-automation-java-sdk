package com.cloudtestapi.test.models;

import com.cloudtestapi.common.AbstractRequest;
import com.cloudtestapi.common.profile.HttpProfile;
import com.google.gson.Gson;
import java.util.HashMap;

public class StartFunctionalTestRequest extends AbstractRequest {
    private FunctionalTest test;

    public StartFunctionalTestRequest(){
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/tests/functional");
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

    public FunctionalTest getTest() {
        return test;
    }

    public void setTest(FunctionalTest test) {
        this.test = test;
    }
}
