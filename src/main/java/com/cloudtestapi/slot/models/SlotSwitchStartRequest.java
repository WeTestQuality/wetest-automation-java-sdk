package com.cloudtestapi.slot.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

public class SlotSwitchStartRequest extends AbstractRequestWithoutSpecificBodyGenerator {
    @SerializedName("slot_out_addr")
    @Expose
    private String slotOutAddr;

    @SerializedName("model_id")
    @Expose
    private int modelId;

    @SerializedName("device_id")
    @Expose
    private int deviceId;

    public SlotSwitchStartRequest(){
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/slot/switch/start");
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

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
        this.deviceId = 0;
    }
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
        this.modelId = 0;
    }
}
