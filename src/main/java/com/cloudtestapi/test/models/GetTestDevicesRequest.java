package com.cloudtestapi.test.models;

import com.cloudtestapi.common.AbstractRequestWithoutBodyAdapter;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class GetTestDevicesRequest extends AbstractRequestWithoutBodyAdapter {
    private Long testId;

    private boolean log;

    private boolean image;

    private boolean error;

    public GetTestDevicesRequest(){
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/tests/:test_id/devices");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {
        map.put("log", log);
        map.put("image", image);
        map.put("error", error);
    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":test_id", testId.toString());
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
