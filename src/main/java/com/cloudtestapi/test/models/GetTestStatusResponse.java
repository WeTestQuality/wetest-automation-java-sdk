package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetTestStatusResponse {
    @Expose
    @SerializedName("test_status")
    public TestStatus testStatus;
}
