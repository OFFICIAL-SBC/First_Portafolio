package com.example.countriesapp.framework

import com.example.countriesapp.usecases.*

data class Interactors(
    val getCountriesByContinentUseCase: GetCountriesByContinentUseCase,
    val getCountryByCapitalUseCase: GetCountryByCapitalUseCase,
    val getCitiesByCountryUseCase: GetCitiesByCountryUseCase,
    val getCountryByCodeUseCase: GetCountryByCodeUseCase,
    val getCityNearToALocationUseCase: GetCityNearToALocationUseCase,
    val getAllSavedLocationsUseCase: GetAllSavedLocationsUseCase,
    val saveCurrentLocationUseCase: SaveCurrentLocationUseCase,
    val deleteSelectedLocationUseCase: DeleteSelectedLocationUseCase
)