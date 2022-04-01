package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FunctionalTest extends TestParam{
    @Expose
    @SerializedName("case_config")
    public CaseConfig caseConfig;

    @Expose
    @SerializedName("case_type")
    public String caseType;
}
