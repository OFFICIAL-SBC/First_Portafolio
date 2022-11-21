package com.example.weatherapp.presentation.Model

data class WeahterDataPresentation(
    val aqi: Int,
    val city_name: String,
    val temp: Double,
    val rh: Int,
    val wind_spd: Double,
    val timezone: String
)
