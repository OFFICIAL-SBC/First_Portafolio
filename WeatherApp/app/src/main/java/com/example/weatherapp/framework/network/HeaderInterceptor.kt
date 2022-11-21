package com.example.weatherapp.framework.network

import com.example.weatherapp.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-RapidAPI-Key", Constants.API_KEY)
            .addHeader("X-RapidAPI-Host",Constants.HOST)
            .build()

        return chain.proceed(request)
    }
}