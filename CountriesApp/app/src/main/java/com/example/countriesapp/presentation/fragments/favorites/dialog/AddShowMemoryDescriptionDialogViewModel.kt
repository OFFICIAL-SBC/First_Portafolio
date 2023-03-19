package com.example.countriesapp.presentation.fragments.favorites.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.framework.CountryViewModel
import com.example.countriesapp.framework.Interactors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddShowMemoryDescriptionDialogViewModel(interactors: Interactors) : CountryViewModel(interactors) {

    private val indicator: MutableLiveData<Boolean> = MutableLiveData()
    val indicatorDone: LiveData<Boolean> = indicator

    fun updateDescription(id: Int, description: String){
        viewModelScope.launch(Dispatchers.IO){
            interactors.updateDescriptionUseCase(id,description)
            indicator.postValue(true)
        }
    }

}