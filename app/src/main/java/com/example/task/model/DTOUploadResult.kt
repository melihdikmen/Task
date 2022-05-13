package com.example.task.model

import com.google.gson.annotations.SerializedName

class DTOUploadResult {

    @SerializedName("status")
    var status: Int = 0;

    @SerializedName("message")
    var message:String? = null
}