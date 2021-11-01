package com.cloudtestapi.upload.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class GetUploadIdFromWTRequest extends AbstractRequestWithoutSpecificBodyGenerator {

    private long size;

    private String project;

    private String name;

    private int csize;

    public GetUploadIdFromWTRequest() {
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/platform/upload/id");
    }


    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {
        map.put("size", size);
        map.put("project", project);
        map.put("name", name);
        map.put("owner", 2);
        map.put("csize", csize);
        map.put("channel", "wetest");
    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {

    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCsize() {
        return csize;
    }

    public void setCsize(int csize) {
        this.csize = csize;
    }
}
