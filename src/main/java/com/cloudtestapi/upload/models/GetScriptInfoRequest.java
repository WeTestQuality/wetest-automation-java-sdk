package com.cloudtestapi.upload.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class GetScriptInfoRequest extends AbstractRequestWithoutSpecificBodyGenerator {
    private Integer scriptId;

    public GetScriptInfoRequest(){
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/scripts/:script");
    }

    public Integer getScriptId() {
        return scriptId;
    }

    public void setScriptId(Integer scriptId) {
        this.scriptId = scriptId;
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":script", scriptId.toString());
    }
}
