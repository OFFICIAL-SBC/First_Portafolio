package com.example.countriesapp.framework

import android.content.Context
import com.example.countriesapp.data.CountryDataSourceDb
import com.example.countriesapp.framework.local.room.CountryDatabase
import com.example.countriesapp.framework.local.room.CountryEntity

class CountryDataSourceDbImpl(context: Context):CountryDataSourceDb {

    private val countryDao = CountryDatabase.getInstance(context).getCountryDao()

    override suspend fun add(currentLocation: CountryEntity) {
        countryDao.saveUbication(currentLocation)
    }

    override suspend fun delete(location: Int) {
        countryDao.deleteUbication(location)
    }

    override suspend fun getAllSavedLocations(): ArrayList<CountryEntity> {
        val auxArrayList= arrayListOf<CountryEntity>()
        auxArrayList.addAll(countryDao.getAllUbicationObjects())
        return auxArrayList
    }

    override suspend fun updateDescription(id:Int,description: String) {
        countryDao.updateDescription(id,description)
    }
}