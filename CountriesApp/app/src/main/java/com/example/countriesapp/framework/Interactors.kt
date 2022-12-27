package com.example.countriesapp.framework

import com.example.countriesapp.usecases.GetCitiesByCountryUseCase
import com.example.countriesapp.usecases.GetCountriesByContinentUseCase
import com.example.countriesapp.usecases.GetCountryByCapitalUseCase
import com.example.countriesapp.usecases.GetCountryByCodeUseCase

data class Interactors(
    val getCountriesByContinentUseCase: GetCountriesByContinentUseCase,
    val getCountryByCapitalUseCase: GetCountryByCapitalUseCase,
    val getCitiesByCountryUseCase: GetCitiesByCountryUseCase,
    val getCountryByCodeUseCase: GetCountryByCodeUseCase
)