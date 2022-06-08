package com.cloudtestapi.slot.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class SlotListDeviceRequest extends AbstractRequestWithoutSpecificBodyGenerator {
    @Expose
    @SerializedName("phone_type_list")
    private int[] phoneTypeList;

    public SlotListDeviceRequest(){
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/slot/list/device");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {

    }

    public int[] getPhoneTypeList() {
        return phoneTypeList;
    }

    public void setPhoneTypeList(int[] phoneTypeList) {
        this.phoneTypeList = phoneTypeList;
    }
}
