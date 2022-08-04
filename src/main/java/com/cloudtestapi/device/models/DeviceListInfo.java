package com.cloudtestapi.device.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceListInfo extends ModelListInfoBase {

    @Expose
    @SerializedName("device_id")
    public Integer deviceId;

    @Expose
    @SerializedName("ram")
    public Integer ram;

    @Expose
    @SerializedName("rom_total")
    public Integer romTotal;

    @Expose
    @SerializedName("user_num")
    public Integer userNum;
}
