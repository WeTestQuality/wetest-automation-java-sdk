package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AutomationTest extends TestParam {

    @SerializedName("parser_type")
    @Expose
    public String parserType;

    @SerializedName("max_case_runtime")
    @Expose
    public int maxCaseRuntime;

    public String getParserType() {
        return parserType;
    }

    public void setParserType(String parserType) {
        this.parserType = parserType;
    }

    public int getMaxCaseRuntime() {
        return maxCaseRuntime;
    }

    public void setMaxCaseRuntime(int maxCaseRuntime) {
        this.maxCaseRuntime = maxCaseRuntime;
    }

}
