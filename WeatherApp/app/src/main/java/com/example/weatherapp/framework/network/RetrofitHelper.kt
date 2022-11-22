package com.example.weatherapp.framework.network

import com.example.weatherapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getInstanceRetrofit():Retrofit{
        val headerInterceptor=HeaderInterceptor()
        val client=OkClient(headerInterceptor)
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_API_REST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.getClient())
            .build()
    }
}