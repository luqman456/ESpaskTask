package com.multisync.esparkapplication.pojo;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    @SerializedName("Devices")
    T data;

    public T getData() {
        return data;
    }
}
