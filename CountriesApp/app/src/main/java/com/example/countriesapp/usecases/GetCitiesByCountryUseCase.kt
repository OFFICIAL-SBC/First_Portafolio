package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryApiRepository
import com.example.countriesapp.domain.CityList
import com.example.countriesapp.utils.Resource

class GetCitiesByCountryUseCase(private val repository: CountryApiRepository) {

    suspend operator fun invoke(id: String): Resource<CityList> {
        return repository.getCitiesByCountry(id)
    }

}