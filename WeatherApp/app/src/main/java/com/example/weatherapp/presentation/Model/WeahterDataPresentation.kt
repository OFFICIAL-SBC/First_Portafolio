package com.example.weatherapp.presentation.Model

import com.example.weatherapp.domain.WeatherDataDomain
import java.io.Serializable

data class WeahterDataPresentation(
    val aqi: Int,
    val city_name: String,
    val temp: Double,
    val rh: Int,
    val wind_spd: Double,
    val timezone: String,
    val description: String,
    val lat: Double,
    val lon: Double
): Serializable


fun WeatherDataDomain.toPresentation() =
    WeahterDataPresentation(
        data[0].aqi,
        data[0].city_name,
        data[0].temp,
        data[0].rh,
        data[0].wind_spd,
        data[0].timezone,
        data[0].weather.description,
        data[0].lat,
        data[0].lon
    )
