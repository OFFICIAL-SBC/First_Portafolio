package com.example.countriesapp.data

import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.domain.CountryItemClass
import retrofit2.Response

interface CountryDataSourceOne {

    suspend fun getCountriesByContinent(continent: String): Response<ArrayList<CountryItemClass>>

    suspend fun getCountryByCapital(capital:String):Response<ArrayList<CountryClass>>

    suspend fun getCountryByCode(code: String): Response<ArrayList<CountryClass>>

}