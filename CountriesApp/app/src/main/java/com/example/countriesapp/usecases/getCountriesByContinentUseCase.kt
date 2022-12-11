package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryApiRepository
import com.example.countriesapp.domain.CountryList
import com.example.countriesapp.utils.Resource

class getCountriesByContinentUseCase(private val respository: CountryApiRepository) {

    suspend operator fun invoke(continent: String): Resource<CountryList>{
        return respository.getCountryByContinent(continent)
    }

}