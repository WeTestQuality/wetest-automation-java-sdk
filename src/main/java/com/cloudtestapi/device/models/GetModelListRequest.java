package com.cloudtestapi.device.models;

import com.cloudtestapi.common.AbstractRequest;
import com.cloudtestapi.common.AbstractRequestWithoutBodyAdapter;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class GetModelListRequest extends AbstractRequestWithoutBodyAdapter {
    private String projectId;
    public GetModelListRequest(){
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
