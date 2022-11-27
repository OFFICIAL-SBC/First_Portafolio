package com.example.weatherapp.presentation.Model

import com.example.weatherapp.domain.WeatherDataDomain
import com.example.weatherapp.framework.local.WeatherEntity
import java.io.Serializable

data class WeatherDataPresentation(
    val id: Int,
    val aqi: Double,
    val city_name: String,
    val temp: Double,
    val rh: Double,
    val wind_spd: Double,
    val timezone: String,
    val description: String,
    val lat: Double,
    val lon: Double,
    val icon_code: String
) : Serializable


fun WeatherDataDomain.toPresentation() =
    WeatherDataPresentation(
        -1,
        data[0].aqi,
        data[0].city_name,
        data[0].temp,
        data[0].rh,
        data[0].wind_spd,
        data[0].timezone,
        data[0].weather.description,
        data[0].lat,
        data[0].lon,
        data[0].weather.icon
    )

fun WeatherEntity.toPresentation() =
    WeatherDataPresentation(
      id, aqi, city_name, temp, rh, wind_spd, timezone, description, lat, lon, icon_code
    )
