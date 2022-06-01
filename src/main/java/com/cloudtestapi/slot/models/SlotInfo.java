package com.cloudtestapi.slot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SlotInfo {
    @SerializedName("slot_out_addr")
    @Expose
    public String slotOutAddr;

    @SerializedName("slot_state")
    @Expose
    public int slotState;

    @SerializedName("slot_token")
    @Expose
    public String slotToken;

    @SerializedName("model_info")
    @Expose
    public ModelInfo modelInfo;
}
