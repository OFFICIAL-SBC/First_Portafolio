package com.example.countriesapp.data

import com.example.countriesapp.domain.CountryClass
import retrofit2.Response

interface CountryDataSourceOne {

    suspend fun getCountriesByContinent(continent: String): Response<ArrayList<CountryClass>>

    suspend fun getCountryByCapital(capital:String):Response<ArrayList<CountryClass>>

}