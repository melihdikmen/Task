package com.example.task.net

import com.example.task.model.DTOProcess
import com.example.task.model.DTOProcessDetail
import com.example.task.model.DTOResponse
import com.example.task.model.DTOUploadResult
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


object ApiRequests {

    private val services: Services = ApiClient.getRetrofitInstance()!!.create(Services::class.java)


    fun getProcess(listener: ApiRequestListener<DTOResponse<ArrayList<DTOProcess>>>) {

        val call: Call<DTOResponse<ArrayList<DTOProcess>>?>? = services.getProcess();
        call!!.enqueue(object : Callback<DTOResponse<ArrayList<DTOProcess>>?> {
            override fun onFailure(call: Call<DTOResponse<ArrayList<DTOProcess>>?>, t: Throwable) {
                listener.onFailure(t)
            }

            override fun onResponse(
                call: Call<DTOResponse<ArrayList<DTOProcess>>?>,
                response: Response<DTOResponse<ArrayList<DTOProcess>>?>
            ) {

                listener.onResponse(response.body()!!)
            }
        })

    }


    fun getProcessDetail(listener: ApiRequestListener<DTOResponse<DTOProcessDetail>>,process:DTOProcess){

        val call: Call<DTOResponse<DTOProcessDetail>?>? = services.getProcessDetail(process.id);

        call!!.enqueue(object : Callback<DTOResponse<DTOProcessDetail>?> {
            override fun onFailure(call: Call<DTOResponse<DTOProcessDetail>?>, t: Throwable) {
                listener.onFailure(t)
            }

            override fun onResponse(
                call: Call<DTOResponse<DTOProcessDetail>?>,
                response: Response<DTOResponse<DTOProcessDetail>?>
            ) {

                listener.onResponse(response.body()!!)
            }
        })
    }


    fun uploadPhoto(listener: ApiRequestListener<DTOUploadResult>,file:File){
        val filePart = MultipartBody.Part.createFormData(
            "file",
            file.name,
            RequestBody.create(MediaType.parse("image/*"), file)
        )

        val call: Call<DTOUploadResult> = services.uploadPhoto(filePart);

        call!!.enqueue(object : Callback<DTOUploadResult> {
            override fun onFailure(call: Call<DTOUploadResult>, t: Throwable) {
                listener.onFailure(t)
            }

            override fun onResponse(
                call: Call<DTOUploadResult>,
                response: Response<DTOUploadResult>
            ) {
                listener.onResponse(response.body()!!)
            }
        })
    }
}