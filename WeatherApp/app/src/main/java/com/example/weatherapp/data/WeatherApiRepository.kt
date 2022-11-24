package com.example.weatherapp.data


import com.example.weatherapp.presentation.Model.WeahterDataPresentation
import com.example.weatherapp.presentation.Model.toPresentation
import com.google.android.gms.maps.model.LatLng

class WeatherApiRepository(private val apiSource: WeatherDataSourceApi) {

    suspend fun getCurrentWeather(location: LatLng): WeahterDataPresentation =
        apiSource.getCurrentWeather(location).toPresentation()


}