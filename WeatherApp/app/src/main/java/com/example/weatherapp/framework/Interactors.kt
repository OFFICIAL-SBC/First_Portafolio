package com.example.weatherapp.framework

import com.example.weatherapp.usecases.GetCurrentWeatherUseCase

data class Interactors(val getCurrentWeatherUseCase: GetCurrentWeatherUseCase)
