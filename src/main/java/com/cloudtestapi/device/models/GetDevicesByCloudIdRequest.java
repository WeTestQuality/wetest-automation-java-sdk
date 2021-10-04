package com.cloudtestapi.device.models;

import com.cloudtestapi.common.AbstractRequest;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class GetDevicesByCloudIdRequest extends AbstractRequest {
    private Integer cloudId;

    public GetDevicesByCloudIdRequest(){
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/clouds/:cloud_id/devices");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":cloud_id", cloudId.toString());
    }

    public Integer getCloudId() {
        return cloudId;
    }

    public void setCloudId(Integer cloudId) {
        this.cloudId = cloudId;
    }
}
