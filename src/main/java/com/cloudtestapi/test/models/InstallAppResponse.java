package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstallAppResponse {
    public class InstallInfo {
        @Expose
        @SerializedName("install_id")
        public Integer install_id;
    }
    @Expose
    @SerializedName("install_info")
    public InstallInfo installInfo;
}
