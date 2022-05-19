package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartDebugConnectResponse {
    public class DebugStatus {
        @Expose
        @SerializedName("addr")
        public String addr;
        @Expose
        @SerializedName("token")
        public String token;
        @Expose
        @SerializedName("authed")
        public Boolean authed;
    }
    @Expose
    @SerializedName("debug_status")
    public DebugStatus debugStatus;
}
