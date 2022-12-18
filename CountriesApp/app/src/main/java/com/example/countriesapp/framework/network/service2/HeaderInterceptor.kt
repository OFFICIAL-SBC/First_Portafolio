package com.example.countriesapp.framework.network.service2

import com.example.countriesapp.utils.Constans
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-RapidAPI-Key",Constans.API_KEY)
            .addHeader("X-RapidAPI-Host",Constans.HOST)
            .build()
        return chain.proceed(request)
    }
}