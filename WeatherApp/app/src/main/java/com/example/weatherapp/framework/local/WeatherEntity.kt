package com.example.weatherapp.framework.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.presentation.Model.WeahterDataPresentation

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "aqi") val aqi: Double,
    @ColumnInfo(name = "city_name") val city_name: String,
    @ColumnInfo(name = "temp") val temp: Double,
    @ColumnInfo(name = "rh") val rh: Double,
    @ColumnInfo(name = "wind_spd") val wind_spd: Double,
    @ColumnInfo(name = "timezone") val timezone: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "lon") val lon: Double,
    @ColumnInfo(name = "icon_code") val icon_code: String
)

fun WeahterDataPresentation.toDatabase() =
    WeatherEntity(
        aqi = aqi,
        city_name = city_name,
        temp = temp,
        rh = rh,
        wind_spd = wind_spd,
        timezone = timezone,
        description = description,
        lat = lat,
        lon = lon,
        icon_code = icon_code
    )

