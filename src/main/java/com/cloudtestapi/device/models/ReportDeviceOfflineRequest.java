package com.cloudtestapi.device.models;

import com.cloudtestapi.common.AbstractRequest;
import com.cloudtestapi.common.AbstractRequestWithoutBodyAdapter;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;


public class ReportDeviceOfflineRequest extends AbstractRequestWithoutBodyAdapter {
    private Integer deviceId;

    public ReportDeviceOfflineRequest(){
        this.setHttpMethod(HttpProfile.REQ_PUT);
        this.withApiInfo("v1", "/devices/:device_id/offline");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":device_id", deviceId.toString());
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}
