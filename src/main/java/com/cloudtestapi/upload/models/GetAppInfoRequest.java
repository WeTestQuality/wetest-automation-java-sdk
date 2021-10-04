package com.cloudtestapi.upload.models;

import com.cloudtestapi.common.AbstractRequest;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class GetAppInfoRequest extends AbstractRequest {

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    private Integer appId;

    public GetAppInfoRequest(){
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/apps/:app_id");
    }



    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {}

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":app_id", appId.toString());
    }
}
