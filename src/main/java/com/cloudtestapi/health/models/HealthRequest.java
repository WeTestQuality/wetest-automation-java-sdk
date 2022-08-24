package com.cloudtestapi.health.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;

import java.util.HashMap;

public class HealthRequest extends AbstractRequestWithoutSpecificBodyGenerator {

    public HealthRequest() {
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("", "healthz");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {

    }
}
