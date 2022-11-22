package com.example.weatherapp.framework.network

import com.example.weatherapp.domain.WeatherDataDomain
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("current")
    suspend fun getCurrentWeatherData(@Query("lon") lon: String, @Query("lat") lat: String):WeatherDataDomain
}