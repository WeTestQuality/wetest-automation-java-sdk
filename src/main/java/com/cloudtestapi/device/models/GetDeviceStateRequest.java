package com.cloudtestapi.device.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class GetDeviceStateRequest extends AbstractRequestWithoutSpecificBodyGenerator {
    private Integer cloudId;

    private Integer deviceId;

    public GetDeviceStateRequest(){
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/clouds/:cloud_id/devices/:device_id/state");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":cloud_id", cloudId.toString());
        map.put(":device_id", deviceId.toString());
    }

    public Integer getCloudId() {
        return cloudId;
    }

    public void setCloudId(Integer cloudId) {
        this.cloudId = cloudId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}
