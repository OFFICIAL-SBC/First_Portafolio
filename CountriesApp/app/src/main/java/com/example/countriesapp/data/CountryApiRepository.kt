package com.example.countriesapp.data

import com.example.countriesapp.domain.CityList
import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.domain.CountryItemClass
import com.example.countriesapp.utils.Resource

class CountryApiRepository(
    private val dataSourceApiOne: CountryDataSourceOne,
    private val dataSourceApiTwo: CountryDataSourceTwo
) : BaseRepo() {

    suspend fun getCountryByContinent(region: String): Resource<ArrayList<CountryItemClass>> {
        return safeApiCall { dataSourceApiOne.getCountriesByContinent(region) }
    }


    suspend fun getCountryByCapitalCity(city: String): Resource<ArrayList<CountryClass>> {
        return safeApiCall { dataSourceApiOne.getCountryByCapital(city) }
    }

    suspend fun getCitiesByCountry(id: String): Resource<CityList>{
        return safeApiCall { dataSourceApiTwo.getCitiesByCountry(id) }
    }

    suspend fun getCountryByCode(code: String): Resource<ArrayList<CountryClass>>{
        return safeApiCall { dataSourceApiOne.getCountryByCode(code) }
    }

    suspend fun getCountryNearToALocation(location: String): Resource<CityList>{
        return safeApiCall { dataSourceApiTwo.getCitiesNearToALocation(location) }
    }
}