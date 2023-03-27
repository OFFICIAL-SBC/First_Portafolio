package com.example.countriesapp.data

import com.example.countriesapp.framework.local.room.CountryEntity

class CountryRoomRepository(private val countryDataSourceDb: CountryDataSourceDb) {

    suspend fun saveLocation (currentLocation: CountryEntity){
        countryDataSourceDb.add(currentLocation)
    }

    suspend fun deleteLocation(selectedLocation: Int){
        countryDataSourceDb.delete(selectedLocation)
    }

    suspend fun getAllLocations():ArrayList<CountryEntity>{
        return countryDataSourceDb.getAllSavedLocations()
    }

    suspend fun updateDescription(id:Int, description: String){
        countryDataSourceDb.updateDescription(id,description)
    }

    suspend fun deleteLocationByDate(date: String){
        countryDataSourceDb.deleteUbicationByDate(date)
    }

}