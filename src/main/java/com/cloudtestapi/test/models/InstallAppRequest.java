package com.cloudtestapi.test.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.CommonTestDeviceRequest;
import com.cloudtestapi.common.profile.HttpProfile;

import java.util.HashMap;

public class InstallAppRequest extends CommonTestDeviceRequest {

    private Integer appId;

    public Boolean getResign() {
        return resign;
    }

    public void setResign(Boolean resign) {
        this.resign = resign;
    }

    private Boolean resign;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public InstallAppRequest() {
        this.setHttpMethod(HttpProfile.REQ_PUT);
        this.withApiInfo("v1", "/tests/:test_id/devices/:device_id/install");
    }
    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {
        map.put("app_id",appId.toString());
        map.put("resign", resign.toString());
    }

}
