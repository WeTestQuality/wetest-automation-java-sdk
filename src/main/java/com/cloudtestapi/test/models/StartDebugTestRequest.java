package com.cloudtestapi.test.models;

import com.cloudtestapi.common.AbstractRequest;
import com.cloudtestapi.common.AbstractRequest;
import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;


public class StartDebugTestRequest extends AbstractRequestWithoutSpecificBodyGenerator {

    private Integer deviceId;

    private String project;

    public StartDebugTestRequest() {
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/tests/debug/devices/:device_id/projects/:project");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":device_id", deviceId.toString());
        map.put(":project", project);
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public StartDebugTestRequest setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getProject() {
        return project;
    }

    public StartDebugTestRequest setProject(String project) {
        this.project = project;
        return this;
    }
}
