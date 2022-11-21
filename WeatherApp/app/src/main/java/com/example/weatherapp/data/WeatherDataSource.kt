package com.example.weatherapp.data

import com.example.weatherapp.domain.WeatherDataDomain
import com.google.android.gms.maps.model.LatLng

interface WeatherDataSourceApi {
    suspend fun getCurrentWeather(location: LatLng):WeatherDataDomain
}