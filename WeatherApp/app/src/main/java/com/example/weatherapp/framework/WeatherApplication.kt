package com.example.weatherapp.framework

import android.app.Application
import com.example.weatherapp.data.WeatherApiRepository
import com.example.weatherapp.data.WeatherDataSourceDb
import com.example.weatherapp.data.WeatherRoomRepository
import com.example.weatherapp.usecases.GetCurrentWeatherUseCase

class WeatherApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        val weatherRoomRepository=WeatherRoomRepository(WeatherDataSourceDbImpl(this))
        val weatherApiRepository=WeatherApiRepository(WeatherDataSourceApiImpl())


        WeatherViewModelFactory.inject(
            Interactors(
                GetCurrentWeatherUseCase(weatherApiRepository)
            )
        )

    }
}