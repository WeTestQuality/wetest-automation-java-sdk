package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetInstallProcessResponse {
    public class InstallInfo {
        @Expose
        @SerializedName("progress")
        public Integer progress;
        @Expose
        @SerializedName("result")
        public Integer result;
        @Expose
        @SerializedName("msg")
        public String msg;
    }

    @Expose
    @SerializedName("install_info")
    public InstallInfo installInfo;
}
