package com.cloudtestapi.upload.models;

import com.cloudtestapi.common.AbstractRequest;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;
import org.omg.PortableInterceptor.Interceptor;

public class GetScriptInfoRequest extends AbstractRequest {
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
