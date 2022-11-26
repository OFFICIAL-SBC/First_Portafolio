package com.example.weatherapp.usecases

import com.example.weatherapp.data.WeatherRoomRepository
import com.example.weatherapp.presentation.Model.WeatherDataPresentation

class DeleteSelectedWeather(private val roomRepository: WeatherRoomRepository) {
    suspend operator fun invoke(currentWeather: WeatherDataPresentation) =
        roomRepository.deleteWeather(currentWeather)
}