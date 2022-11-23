package com.example.weatherapp.framework.network

import com.example.weatherapp.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val headerInterceptor = HeaderInterceptor()

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)
        .build()

    fun getInstanceRetrofit():Retrofit{

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_API_REST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()
    }
}