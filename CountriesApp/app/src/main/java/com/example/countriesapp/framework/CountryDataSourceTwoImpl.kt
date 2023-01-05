package com.example.countriesapp.framework

import com.example.countriesapp.data.CountryDataSourceTwo
import com.example.countriesapp.domain.CityList
import com.example.countriesapp.framework.network.service2.CountryApiClient2
import retrofit2.Response

class CountryDataSourceTwoImpl: CountryDataSourceTwo {

    private val retroClient2 = CountryApiClient2()

    override suspend fun getCitiesByCountry(countryId: String): Response<CityList> {
        val result: Response<CityList> =retroClient2.getCitiesByCountry(countryId)
        return result
    }

    override suspend fun getCitiesNearToALocation(location: String): Response<CityList> {
        val result = retroClient2.getCitiesNearToALocation(location)
        return result
    }
}