package com.example.countriesapp.framework.network.service2

import com.example.countriesapp.utils.Constans
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper2 {
    private val loggin = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val headerInterceptor = HeaderInterceptor()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggin)
        .addInterceptor(headerInterceptor)
        .build()

    fun getInstanceRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constans.BASE_URL_TWO)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}