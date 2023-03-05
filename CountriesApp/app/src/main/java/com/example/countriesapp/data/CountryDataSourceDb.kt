package com.example.countriesapp.data

import com.example.countriesapp.framework.local.room.CountryEntity

interface CountryDataSourceDb {

    suspend fun add(currentLocation: CountryEntity)

    suspend fun delete(location:CountryEntity)

    suspend fun getAllSavedLocations(): ArrayList<CountryEntity>

}