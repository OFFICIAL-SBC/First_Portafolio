package com.example.weatherapp.framework.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherEntity::class],version = 1)
abstract class WeatherDatabase:RoomDatabase() {

    companion object{
        private const val NAME_DATABASE = "weather.db"

        private var instance: WeatherDatabase? = null

        private fun create(context: Context): WeatherDatabase=
            Room.databaseBuilder(context,WeatherDatabase::class.java, NAME_DATABASE)
                .build()


        fun getInstance(context: Context)=
            (instance ?: create(context)).also { instance = it}
    }

    abstract fun getWeatherDao():WeatherDao

}