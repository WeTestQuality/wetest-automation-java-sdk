package com.cloudtestapi.device.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;

import java.util.HashMap;

public class GetModelListInfoRequest extends AbstractRequestWithoutSpecificBodyGenerator {
    private Integer listId;

    public GetModelListInfoRequest() {
        this.setHttpMethod(HttpProfile.REQ_GET);
        this.withApiInfo("v1", "/model/list/:list_id/info");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":list_id", listId.toString());
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }
}
