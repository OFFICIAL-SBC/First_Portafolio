package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryApiRepository
import com.example.countriesapp.domain.CityList
import com.example.countriesapp.utils.Resource

class GetCityNearToALocationUseCase(private val repository: CountryApiRepository) {

    suspend operator fun invoke(location: String): Resource<CityList>{
        return repository.getCountryNearToALocation(location)
    }
}