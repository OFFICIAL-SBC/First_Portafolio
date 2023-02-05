package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryRoomRepository
import com.example.countriesapp.framework.local.room.CountryEntity

class DeleteSelectedLocationUseCase(private val repository: CountryRoomRepository) {

    suspend operator fun invoke(selectedLocation: CountryEntity){
        repository.deleteLocation(selectedLocation)
    }

}