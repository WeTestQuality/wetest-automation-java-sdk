package com.cloudtestapi.device.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;

import java.util.HashMap;

public class GetProjectModelListRequest extends AbstractRequestWithoutSpecificBodyGenerator {

    private String projectId;

    private Integer offset;

    private Integer limit;

    private Integer filterType;

    public GetProjectModelListRequest() {
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/model/list");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {
        map.put("project", projectId);
        map.put("filtertype", filterType);
        map.put("offset", offset);
        map.put("limit", limit);
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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        if (offset < 0) {
            offset = 0;
        }
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        if (limit < 0) {
            limit = 0;
        }
        this.limit = limit;
    }
}
