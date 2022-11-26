package com.example.weatherapp.data


import com.example.weatherapp.presentation.Model.WeatherDataPresentation
import com.example.weatherapp.presentation.Model.toPresentation
import com.google.android.gms.maps.model.LatLng

class WeatherApiRepository(private val apiSource: WeatherDataSourceApi) {

    suspend fun getCurrentWeather(location: LatLng): WeatherDataPresentation =
        apiSource.getCurrentWeather(location).toPresentation()


}