package com.example.countriesapp.data

import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.utils.Resource

class CountryApiRepository(private val dataSourceApiOne: CountryDataSourceOne):BaseRepo() {

    suspend fun getCountryByContinent(region: String): Resource<ArrayList<CountryClass>> {
        return safeApiCall { dataSourceApiOne.getCountriesByContinent(region) }
    }


    suspend fun getCountryByCapitalCity(city: String): Resource<ArrayList<CountryClass>> {
        return safeApiCall { dataSourceApiOne.getCountryByCapital(city) }
    }

}