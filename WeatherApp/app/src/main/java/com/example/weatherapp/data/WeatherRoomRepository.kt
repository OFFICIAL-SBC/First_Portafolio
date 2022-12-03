package com.example.weatherapp.data

import com.example.weatherapp.framework.local.toDatabase
import com.example.weatherapp.presentation.Model.WeatherDataPresentation
import com.example.weatherapp.presentation.Model.toPresentation


class WeatherRoomRepository(private val weatherDataSourceDb: WeatherDataSourceDb) {

    suspend fun saveCurrentWeather(currentWeather: WeatherDataPresentation){
        weatherDataSourceDb.add(currentWeather.toDatabase())
    }

    suspend fun deleteWeather(currentWeather: WeatherDataPresentation){
        weatherDataSourceDb.delete(currentWeather.toDatabase())
    }

    suspend fun getAllWeatherObjects():List<WeatherDataPresentation>{
        return weatherDataSourceDb.getAllSaveWeathers().map{it.toPresentation()}
    }

}