package com.example.countriesapp.data

import com.example.countriesapp.domain.CountryList
import com.example.countriesapp.utils.Resource

class CountryApiRepository(private val dataSourceApiOne: CountryDataSourceOne):BaseRepo() {

    suspend fun getCountryByContinent(region: String): Resource<CountryList> {
        return safeApiCall { dataSourceApiOne.getCountriesByContinent(region) }
    }


    suspend fun getCountryByCapitalCity(city: String): Resource<CountryList> {
        return safeApiCall { dataSourceApiOne.getCountryByCapital(city) }
    }

}