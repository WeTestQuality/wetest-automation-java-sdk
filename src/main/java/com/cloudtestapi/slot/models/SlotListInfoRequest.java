package com.cloudtestapi.slot.models;

import com.cloudtestapi.common.AbstractRequest;
import com.cloudtestapi.common.AbstractRequestWithoutBodyAdapter;
import com.cloudtestapi.common.profile.HttpProfile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

public class SlotListInfoRequest extends AbstractRequestWithoutBodyAdapter {

    @Expose
    @SerializedName("slot_out_addr")
    private String slotOutAddr;

    public SlotListInfoRequest(){
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/slot/list/info");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {

    }

    public String getSlotOutAddr() {
        return slotOutAddr;
    }

    public void setSlotOutAddr(String slotOutAddr) {
        this.slotOutAddr = slotOutAddr;
    }
}
