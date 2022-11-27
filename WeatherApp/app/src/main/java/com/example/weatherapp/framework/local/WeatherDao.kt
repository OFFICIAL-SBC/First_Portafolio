package com.example.weatherapp.framework.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather_table")
    suspend fun getAllWeatherObjects():List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeather(weather: WeatherEntity)

    @Query("DELETE FROM weather_table WHERE id = :weather_id")
    suspend fun deleteWeather (weather_id: Int)

}