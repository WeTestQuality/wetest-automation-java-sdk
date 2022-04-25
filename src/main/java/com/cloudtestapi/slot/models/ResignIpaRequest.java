package com.cloudtestapi.slot.models;

import com.cloudtestapi.common.AbstractRequestWithoutSpecificBodyGenerator;
import com.cloudtestapi.common.profile.HttpProfile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

public class ResignIpaRequest extends AbstractRequestWithoutSpecificBodyGenerator {
    @SerializedName("slot_out_addr")
    @Expose
    private String slotOutAddr;

    @SerializedName("file_url")
    @Expose
    private String fileUrl;

    public ResignIpaRequest(){
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/slot/resign/file");
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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
