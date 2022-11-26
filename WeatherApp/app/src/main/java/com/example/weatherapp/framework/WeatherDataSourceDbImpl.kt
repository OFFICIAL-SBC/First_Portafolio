package com.example.weatherapp.framework

import android.content.Context
import com.example.weatherapp.data.WeatherDataSourceDb
import com.example.weatherapp.framework.local.WeatherDatabase
import com.example.weatherapp.framework.local.WeatherEntity

class WeatherDataSourceDbImpl(context:Context):WeatherDataSourceDb {

    private val weatherDao = WeatherDatabase.getInstance(context).getWeatherDao()

    override suspend fun add(currentWeather: WeatherEntity) {
        weatherDao.saveWeather(currentWeather)
    }

    override suspend fun delete(currentWeather: WeatherEntity) {

        weatherDao.deleteWeather(currentWeather)

    }

    override suspend fun getAllSaveWeathers(): List<WeatherEntity> {
        return weatherDao.getAllWeatherObjects()
    }
}