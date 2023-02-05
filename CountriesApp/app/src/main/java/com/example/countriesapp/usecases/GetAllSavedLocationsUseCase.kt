package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryRoomRepository
import com.example.countriesapp.framework.local.room.CountryEntity

class GetAllSavedLocationsUseCase(private val repository: CountryRoomRepository) {

    suspend operator fun invoke ():List<CountryEntity>{
        return repository.getAllLocations()
    }

}