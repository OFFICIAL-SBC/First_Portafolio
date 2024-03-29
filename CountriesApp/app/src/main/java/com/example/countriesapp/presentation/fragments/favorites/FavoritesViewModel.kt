package com.example.countriesapp.presentation.fragments.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.framework.CountryViewModel
import com.example.countriesapp.framework.Interactors
import com.example.countriesapp.framework.local.room.CountryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(interactors: Interactors) : CountryViewModel(interactors) {

    private val savedPlaces: MutableLiveData<ArrayList<CountryEntity>> = MutableLiveData()
    val savedPlacesDone: LiveData<ArrayList<CountryEntity>> = savedPlaces

    private val indicator: MutableLiveData<Boolean> = MutableLiveData()
    val indicatorDone: LiveData<Boolean> = indicator

    fun getAllSavedPlaces(){
        viewModelScope.launch(Dispatchers.IO){
            savedPlaces.postValue(interactors.getAllSavedLocationsUseCase())
        }
    }

    fun deleteSelectedLocation(selected: Int){
        viewModelScope.launch(Dispatchers.IO){
            interactors.deleteSelectedLocationUseCase(selected)
            indicator.postValue(true)
        }
    }


}