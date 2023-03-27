package com.example.countriesapp.usecases

import com.example.countriesapp.data.CountryRoomRepository

class DeleteSelectedLocationByDateUseCase(private val roomRepository: CountryRoomRepository) {

    suspend operator fun invoke(date:String){
        roomRepository.deleteLocationByDate(date)
    }

}