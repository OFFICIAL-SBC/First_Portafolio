package com.example.countriesapp.framework

import com.example.countriesapp.usecases.getCountriesByContinentUseCase
import com.example.countriesapp.usecases.getCountryByCapitalUseCase

data class Interactors(
    val getCountriesByContinentUseCase: getCountriesByContinentUseCase,
    val getCountryByCapitalUseCase: getCountryByCapitalUseCase
)