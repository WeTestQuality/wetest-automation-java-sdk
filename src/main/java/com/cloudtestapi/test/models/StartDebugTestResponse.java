package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartDebugTestResponse {

    @Expose
    @SerializedName("debug_test_info")
    public DebugTestInfo debugTestInfo;
}
