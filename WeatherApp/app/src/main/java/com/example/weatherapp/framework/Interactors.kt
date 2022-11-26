package com.example.weatherapp.framework

import com.example.weatherapp.usecases.DeleteSelectedWeatherUseCase
import com.example.weatherapp.usecases.GetCurrentWeatherUseCase
import com.example.weatherapp.usecases.GetListSavedWeatherObjectsUseCase
import com.example.weatherapp.usecases.SaveCurrentWeatherUseCase

data class Interactors(
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    val deleteSelectedWeatherUseCase: DeleteSelectedWeatherUseCase,
    val getListSavedWeatherObjectsUseCase: GetListSavedWeatherObjectsUseCase,
    val saveCurrentWeatherUseCase: SaveCurrentWeatherUseCase
)
