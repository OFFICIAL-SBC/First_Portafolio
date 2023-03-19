package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryRoomRepository

class UpdateDescriptionUseCase(private val respository: CountryRoomRepository) {
    suspend operator fun invoke(id:Int, description: String){
        respository.updateDescription(id,description)
    }
}