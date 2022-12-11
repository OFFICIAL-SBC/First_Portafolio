package com.example.countriesapp.framework

import com.example.countriesapp.data.CountryDataSourceOne
import com.example.countriesapp.domain.CountryList
import com.example.countriesapp.framework.network.CountryApiClient
import retrofit2.Response

class CountryDataSourceOneImpl:CountryDataSourceOne {

    private val client = CountryApiClient()

    override suspend fun getCountriesByContinent(continent: String): Response<CountryList> {
        return client.getCountriesByRegion(continent)
    }

    override suspend fun getCountryByCapital(capital: String): Response<CountryList> {
        return client.getCountryByCapital(capital)
    }
}