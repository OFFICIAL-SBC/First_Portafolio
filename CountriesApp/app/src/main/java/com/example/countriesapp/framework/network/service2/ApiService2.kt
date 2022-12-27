package com.example.countriesapp.framework.network.service2

import com.example.countriesapp.domain.CityList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService2 {

    @GET("cities")
    suspend fun getCitiesFromAcountry(@Query("countryIds") countryId: String): Response<CityList>
}