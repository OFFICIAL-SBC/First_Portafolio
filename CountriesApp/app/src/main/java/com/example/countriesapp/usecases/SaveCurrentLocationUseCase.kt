package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryRoomRepository
import com.example.countriesapp.framework.local.room.CountryEntity

class SaveCurrentLocationUseCase(private val repository: CountryRoomRepository) {

    suspend operator fun invoke(currentLocation:CountryEntity){
        repository.saveLocation(currentLocation)
    }

}