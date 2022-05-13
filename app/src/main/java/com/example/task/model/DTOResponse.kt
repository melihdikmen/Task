package com.example.task.model

import com.google.gson.annotations.SerializedName

class DTOResponse<T> {

    @SerializedName("status")
    var status: Int = 0;

    @SerializedName("data")
    var data: T? = null;
}