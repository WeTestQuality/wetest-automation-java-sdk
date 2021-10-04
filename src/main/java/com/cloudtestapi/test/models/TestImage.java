package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestImage {
    @Expose
    @SerializedName("image_name")
    public String imageName;

    @Expose
    @SerializedName("image_time")
    public String imageTime;

    @Expose
    @SerializedName("image_url")
    public String imageUrl;
}
