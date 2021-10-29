package com.cloudtestapi.device.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class RebootDeviceRequest extends AbstractRequestWithoutSpecificBodyGenerator {

    private Integer deviceId;

    public RebootDeviceRequest() {
        this.setHttpMethod(HttpProfile.REQ_PUT);
        this.withApiInfo("v1", "/devices/:device_id/reboot");
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
