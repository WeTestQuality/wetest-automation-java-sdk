package com.cloudtestapi.device.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class GetModelListRequest extends AbstractRequestWithoutSpecificBodyGenerator {

    private String projectId;

    public GetModelListRequest() {
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/model/list/devops");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {
        map.put("project_id", projectId);
    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {

    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
