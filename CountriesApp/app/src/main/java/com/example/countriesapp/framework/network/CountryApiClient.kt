package com.example.countriesapp.framework.network

import com.example.countriesapp.domain.CountryList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CountryApiClient {

    private val retrofit = RetrofitHelper.getInstanceRetrofit()

    suspend fun getCountriesByRegion(continent: String): Response<CountryList>{
        return withContext(Dispatchers.IO){
            retrofit.create(ApiService::class.java).getCountriesByRegion(continent)
        }
    }

    suspend fun getCountryByCapital(capital: String): Response<CountryList>{
        return withContext(Dispatchers.IO){
            retrofit.create(ApiService::class.java).getCountryByCapital(capital)
        }
    }



}