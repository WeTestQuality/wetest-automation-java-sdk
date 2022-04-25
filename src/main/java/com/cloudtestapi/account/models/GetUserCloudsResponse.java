package com.cloudtestapi.account.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserCloudsResponse {
    @Expose
    @SerializedName("clouds")
    public Cloud[] clouds;
}
