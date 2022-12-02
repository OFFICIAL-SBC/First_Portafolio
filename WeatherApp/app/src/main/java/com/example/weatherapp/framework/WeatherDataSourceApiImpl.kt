package com.example.weatherapp.framework

import com.example.weatherapp.data.WeatherDataSourceApi
import com.example.weatherapp.domain.WeatherDataDomain
import com.example.weatherapp.framework.network.WeatherApiClient
import com.google.android.gms.maps.model.LatLng
import retrofit2.Response

class WeatherDataSourceApiImpl:WeatherDataSourceApi {

    private val apiClient = WeatherApiClient()

    override suspend fun getCurrentWeather(location: LatLng): Response<WeatherDataDomain>  {
        val response: Response<WeatherDataDomain> = apiClient.getCurrentWeather(location)
        return response
    }
}