package com.example.countriesapp.framework

import com.example.countriesapp.data.CountryDataSourceOne
import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.domain.CountryItemClass
import com.example.countriesapp.framework.network.service1.CountryApiClient
import retrofit2.Response

class CountryDataSourceOneImpl:CountryDataSourceOne {

    private val client = CountryApiClient()

    override suspend fun getCountriesByContinent(continent: String): Response<ArrayList<CountryItemClass>> {
        return client.getCountriesByRegion(continent)
    }

    override suspend fun getCountryByCapital(capital: String): Response<ArrayList<CountryClass>> {
        return client.getCountryByCapital(capital)
    }
}