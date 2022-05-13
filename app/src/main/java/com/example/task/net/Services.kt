package com.example.task.net

import com.example.task.model.DTOProcess
import com.example.task.model.DTOProcessDetail
import com.example.task.model.DTOResponse
import com.example.task.model.DTOUploadResult
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface Services {
    @GET("index")
    fun getProcess(
    ): Call<DTOResponse<ArrayList<DTOProcess>>?>?


    @GET("show/{id}")
    fun getProcessDetail(@Path("id") id:String
    ): Call<DTOResponse<DTOProcessDetail>?>?



    @Multipart
    @POST("store")
        fun uploadPhoto(@Part filePart: MultipartBody.Part) :Call<DTOUploadResult>
}