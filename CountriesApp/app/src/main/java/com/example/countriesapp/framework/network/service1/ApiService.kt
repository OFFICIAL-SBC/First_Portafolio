package com.example.countriesapp.framework.network.service1

import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.domain.CountryItemClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("region/{region}")
    suspend fun getCountriesByRegion(@Path("region") region: String): Response<ArrayList<CountryItemClass>>

    @GET("capital/{capital}")
    suspend fun getCountryByCapital(@Path("capital") capitalCity: String): Response<ArrayList<CountryClass>>
}