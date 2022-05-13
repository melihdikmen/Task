package com.example.task.net

interface ApiRequestListener<T> {
    fun onResponse(response: T)
    fun onFailure(t: Throwable?)


}