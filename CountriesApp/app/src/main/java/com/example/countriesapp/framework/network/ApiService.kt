package com.example.countriesapp.framework.network

import com.example.countriesapp.domain.CountryList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("region/{region}")
    suspend fun getCountriesByRegion(@Path("region") region: String): Response<CountryList>

    @GET("capital/{capital}")
    suspend fun getCountryByCapital(@Path("capital") capitalCity: String): Response<CountryList>
}