package com.example.weatherapp.usecases

import com.example.weatherapp.data.WeatherApiRepository
import com.example.weatherapp.presentation.Model.WeahterDataPresentation
import com.google.android.gms.maps.model.LatLng

class GetCurrentWeatherUseCase(private val weatherApiRepository: WeatherApiRepository) {
    suspend operator fun invoke(location: LatLng): WeahterDataPresentation =
        weatherApiRepository.getCurrentWeather(location)
}