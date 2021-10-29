package com.cloudtestapi.account.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class GetUserProjectRequest extends AbstractRequestWithoutSpecificBodyGenerator {

    private String user;

    public GetUserProjectRequest() {
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/users/projects");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {
    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {

    }
}
