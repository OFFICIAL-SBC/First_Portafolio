package com.example.weatherapp.data

import com.example.weatherapp.framework.local.WeatherEntity

interface WeatherDataSourceDb {

    suspend fun add(currentWeather: WeatherEntity)

}