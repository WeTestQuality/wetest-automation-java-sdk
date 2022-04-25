package com.cloudtestapi.upload.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;

import java.util.HashMap;

public class GetAppByMD5Request extends AbstractRequestWithoutSpecificBodyGenerator {

    private String md5;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public GetAppByMD5Request() {
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/apps");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {
        map.put("m", md5);
    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {

    }
}