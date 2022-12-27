package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryApiRepository
import com.example.countriesapp.domain.CountryItemClass
import com.example.countriesapp.utils.Resource

class GetCountriesByContinentUseCase(private val respository: CountryApiRepository) {

    suspend operator fun invoke(continent: String): Resource<ArrayList<CountryItemClass>>{
        return respository.getCountryByContinent(continent)
    }

}