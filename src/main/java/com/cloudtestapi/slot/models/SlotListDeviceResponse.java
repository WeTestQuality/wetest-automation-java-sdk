package com.cloudtestapi.slot.models;

import com.cloudtestapi.device.models.DeviceBasicInfo;
import com.google.gson.annotations.Expose;

public class SlotListDeviceResponse {
    @Expose
    public SlotDeviceInfo[] list;
}
