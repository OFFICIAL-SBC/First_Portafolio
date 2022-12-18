package com.example.countriesapp.framework

import com.example.countriesapp.usecases.GetCountriesByContinentUseCase
import com.example.countriesapp.usecases.getCountryByCapitalUseCase

data class Interactors(
    val getCountriesByContinentUseCase: GetCountriesByContinentUseCase,
    val getCountryByCapitalUseCase: getCountryByCapitalUseCase
)