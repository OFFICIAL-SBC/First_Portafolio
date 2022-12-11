package com.example.countriesapp.data

import com.example.countriesapp.domain.CountryList
import retrofit2.Response

interface CountryDataSourceOne {

    suspend fun getCountriesByContinent(continent: String): Response<CountryList>

    suspend fun getCountryByCapital(capital:String):Response<CountryList>

}