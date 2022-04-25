package com.cloudtestapi.common;

import java.util.HashMap;

public class CommonTestRequest extends AbstractRequestWithoutSpecificBodyGenerator {
    private Long testId;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":test_id", testId.toString());
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }
}
