package com.cloudtestapi.device.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelListInfo extends ModelListInfoBase {

    @Expose
    @SerializedName("id")
    public Integer modelId;

    @Expose
    @SerializedName("alias")
    public String alias;

    @Expose
    @SerializedName("manu")
    public String manu;

    @Expose
    @SerializedName("device_state")
    public Integer deviceState;
}
