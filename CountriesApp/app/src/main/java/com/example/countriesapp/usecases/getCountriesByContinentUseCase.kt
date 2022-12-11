package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryApiRepository
import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.utils.Resource

class getCountriesByContinentUseCase(private val respository: CountryApiRepository) {

    suspend operator fun invoke(continent: String): Resource<ArrayList<CountryClass>>{
        return respository.getCountryByContinent(continent)
    }

}