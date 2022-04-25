package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDebugStatusResponse {
    public class AdbStatus {
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
    @SerializedName("adb_status")
    public AdbStatus adbStatus;
}
