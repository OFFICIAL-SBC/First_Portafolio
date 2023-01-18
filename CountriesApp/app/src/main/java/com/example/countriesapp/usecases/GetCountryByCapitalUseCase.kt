package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryApiRepository
import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.utils.Resource

class GetCountryByCapitalUseCase(private val repository: CountryApiRepository) {
    suspend operator fun invoke(capital: String): Resource<ArrayList<CountryClass>>{
        return repository.getCountryByCapitalCity(capital)
    }
}