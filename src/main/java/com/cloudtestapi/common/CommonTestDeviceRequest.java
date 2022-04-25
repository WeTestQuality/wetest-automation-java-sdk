package com.cloudtestapi.common;

import java.util.HashMap;

public class CommonTestDeviceRequest extends AbstractRequestWithoutSpecificBodyGenerator {
    private Integer deviceId;
    private Long testId;
    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":test_id", testId.toString());
        map.put(":device_id", deviceId.toString());
    }
}
