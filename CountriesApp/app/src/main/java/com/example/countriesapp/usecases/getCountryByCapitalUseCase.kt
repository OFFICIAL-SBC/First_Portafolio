package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryApiRepository
import com.example.countriesapp.domain.CountryList
import com.example.countriesapp.utils.Resource

class getCountryByCapitalUseCase(private val repository: CountryApiRepository) {
    suspend operator fun invoke(capital: String): Resource<CountryList>{
        return repository.getCountryByCapitalCity(capital)
    }
}