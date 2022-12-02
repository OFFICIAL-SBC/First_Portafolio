package com.example.weatherapp.usecases

import com.example.weatherapp.data.WeatherApiRepository
import com.example.weatherapp.presentation.Model.WeatherDataPresentation
import com.example.weatherapp.utils.Resource
import com.google.android.gms.maps.model.LatLng

class GetCurrentWeatherUseCase(private val weatherApiRepository: WeatherApiRepository) {
    suspend operator fun invoke(location: LatLng): Resource<WeatherDataPresentation> =
        weatherApiRepository.getCurrentWeather(location)
}