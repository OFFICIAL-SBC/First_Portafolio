package com.example.weatherapp.framework.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient

class OkClient(private val interceptor: Interceptor) {
    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}