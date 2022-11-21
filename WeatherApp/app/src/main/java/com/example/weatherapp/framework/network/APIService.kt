package com.example.weatherapp.framework.network

import com.example.weatherapp.domain.WeatherDataDomain
import retrofit2.http.GET

interface APIService {

    @GET("current")
    suspend fun getCurrentWeatherData():WeatherDataDomain
}