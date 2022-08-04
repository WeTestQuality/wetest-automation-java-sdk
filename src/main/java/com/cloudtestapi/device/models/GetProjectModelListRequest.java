package com.cloudtestapi.device.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;

import java.util.HashMap;

public class GetProjectModelListRequest extends AbstractRequestWithoutSpecificBodyGenerator {

    private String projectId;

    private Integer filterType;

    public GetProjectModelListRequest() {
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/model/list");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {
        map.put("project", projectId);
        map.put("filtertype", filterType);
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

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }
}
