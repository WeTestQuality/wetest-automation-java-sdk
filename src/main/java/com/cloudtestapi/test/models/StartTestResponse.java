package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartTestResponse {

    @Expose
    @SerializedName("test_info")
    public TestInfo testInfo;
}
