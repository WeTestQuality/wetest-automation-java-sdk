package com.cloudtestapi.upload.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DumpScriptResponse {

    @Expose
    @SerializedName("script")
    public Script script;
}
