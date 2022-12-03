package com.example.weatherapp.data

import com.example.weatherapp.framework.local.WeatherEntity

interface WeatherDataSourceDb {

    suspend fun add(currentWeather: WeatherEntity)

    suspend fun delete(currentWeather: WeatherEntity)

    suspend fun getAllSaveWeathers():List<WeatherEntity>

}