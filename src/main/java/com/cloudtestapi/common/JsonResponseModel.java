package com.cloudtestapi.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonResponseModel<T> {

    @SerializedName("data")
    @Expose
    public T data;
}
