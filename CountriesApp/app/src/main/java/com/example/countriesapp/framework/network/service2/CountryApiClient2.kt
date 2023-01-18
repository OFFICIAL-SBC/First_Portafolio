package com.example.countriesapp.framework.network.service2

import com.example.countriesapp.domain.CityList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CountryApiClient2 {

    private val retrofit = RetrofitHelper2.getInstanceRetrofit()

    suspend fun getCitiesByCountry(countryId: String):Response<CityList>{
        return withContext(Dispatchers.IO){
            retrofit.create(ApiService2::class.java).getCitiesFromAcountry(countryId)
        }
    }

    suspend fun getCitiesNearToALocation(location: String, radio: Int): Response<CityList>{
        return withContext(Dispatchers.IO){
            retrofit.create(ApiService2::class.java).getCitiesNearToALocation(location, radio)
        }
    }

}