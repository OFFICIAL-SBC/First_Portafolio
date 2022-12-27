package com.example.countriesapp.framework.network.service1

import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.domain.CountryItemClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CountryApiClient {

    private val retrofit = RetrofitHelper.getInstanceRetrofit()

    suspend fun getCountriesByRegion(continent: String): Response<ArrayList<CountryItemClass>>{
        return withContext(Dispatchers.IO){
            retrofit.create(ApiService::class.java).getCountriesByRegion(continent)
        }
    }

    suspend fun getCountryByCapital(capital: String): Response<ArrayList<CountryClass>>{
        return withContext(Dispatchers.IO){
            retrofit.create(ApiService::class.java).getCountryByCapital(capital)
        }
    }

    suspend fun getCountyByCode(code: String): Response<ArrayList<CountryClass>>{
        return withContext(Dispatchers.IO){
            retrofit.create(ApiService::class.java).getCountryByCode(code)
        }
    }



}