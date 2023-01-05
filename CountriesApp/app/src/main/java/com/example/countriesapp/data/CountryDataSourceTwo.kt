package com.example.countriesapp.data

import com.example.countriesapp.domain.CityList
import retrofit2.Response

interface CountryDataSourceTwo {

    suspend fun getCitiesByCountry(countryId: String): Response<CityList>

    suspend fun getCitiesNearToALocation(location: String): Response<CityList>

}