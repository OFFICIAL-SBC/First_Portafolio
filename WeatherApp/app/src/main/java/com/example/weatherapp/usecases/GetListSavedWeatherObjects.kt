package com.example.weatherapp.usecases

import com.example.weatherapp.data.WeatherRoomRepository
import com.example.weatherapp.presentation.Model.WeatherDataPresentation

class GetListSavedWeatherObjects(private val roomRepository: WeatherRoomRepository) {
    suspend operator fun invoke(): List<WeatherDataPresentation> =
        roomRepository.getAllWeatherObjects()
}