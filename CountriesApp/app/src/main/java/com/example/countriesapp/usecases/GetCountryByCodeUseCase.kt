package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryApiRepository
import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.utils.Resource

class GetCountryByCodeUseCase(private val repository: CountryApiRepository) {

    suspend operator fun invoke(code: String): Resource<ArrayList<CountryClass>>{
        return repository.getCountryByCode(code)
    }

}