package com.cloudtestapi.account.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserProjectResponse {

    @Expose
    @SerializedName("projects")
    public Project[] projects;
}
