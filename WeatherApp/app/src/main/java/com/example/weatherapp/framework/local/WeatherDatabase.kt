package com.example.weatherapp.framework.local

import androidx.room.Database

@Database(entities = [WeatherEntity::class],version = 1)
abstract class WeatherDatabase {

}