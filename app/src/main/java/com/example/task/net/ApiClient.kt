package com.example.task.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object  ApiClient {
    private var retrofit: Retrofit? = null

     fun getRetrofitInstance(): Retrofit? {

        if (retrofit == null) {
            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
                .connectTimeout(1,TimeUnit.MINUTES)
                .readTimeout(1,TimeUnit.MINUTES)
                .writeTimeout(1,TimeUnit.MINUTES);

                this.retrofit = Retrofit.Builder()
                    .baseUrl("https://task-21.herokuapp.com/")
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }

}