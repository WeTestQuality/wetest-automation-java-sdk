package com.cloudtestapi.upload.models;

import com.cloudtestapi.common.AbstractRequest;
import com.cloudtestapi.common.profile.HttpProfile;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class ResignDeviceIPARequest extends AbstractRequest {
    @SerializedName("device_id")
    @Expose
    private Integer deviceId;

    @SerializedName("ipa_url")
    @Expose
    private String ipaUrl;

    public ResignDeviceIPARequest(){
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/ipa/resign");
    }


    @Override
    protected byte[] toBody() {
        Gson gson = new Gson();
        Map m = new HashMap<String,Object>();
        m.put("device_id", deviceId);
        m.put("ipa_url", ipaUrl);
        return gson.toJson(m).getBytes();
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getIpaUrl() {
        return ipaUrl;
    }

    public void setIpaUrl(String ipaUrl) {
        this.ipaUrl = ipaUrl;
    }

}
