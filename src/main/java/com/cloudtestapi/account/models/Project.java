package com.cloudtestapi.account.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Project {

    @Expose
    @SerializedName("project_name")
    public String projectName;

    @Expose
    @SerializedName("project_id")
    public String projectId;

    @Expose
    @SerializedName("key_name")
    public String keyName;
}
