package com.example.countriesapp.presentation.fragments.tabubication.current

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.framework.CountryViewModel
import com.example.countriesapp.framework.Interactors
import com.example.countriesapp.framework.local.room.CountryEntity
import com.example.countriesapp.presentation.models.UbicationClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowLocationViewModel(interactors: Interactors) : CountryViewModel(interactors){

    fun addCurrentLocation(date: String, address: String, path: String){
        val entity = CountryEntity(date = date, address = address, photoPath = path, description = "")
        viewModelScope.launch(Dispatchers.IO){
            interactors.saveCurrentLocationUseCase(entity)
        }
    }

}