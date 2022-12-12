package com.cloudtestapi.slot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SlotDeviceInfo extends ModelInfo {
    @Expose
    @SerializedName("device_state_code")
    public int deviceStateCode;

}
