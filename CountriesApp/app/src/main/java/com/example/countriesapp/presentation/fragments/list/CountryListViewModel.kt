package com.example.countriesapp.presentation.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.framework.CountryViewModel
import com.example.countriesapp.framework.Interactors
import com.example.countriesapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryListViewModel(interactors: Interactors) : CountryViewModel(interactors){
    private val countries: MutableLiveData<ArrayList<CountryClass>> = MutableLiveData()
    val countriesDone: LiveData<ArrayList<CountryClass>> = countries

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val progressBarIndicator: MutableLiveData<Boolean> = MutableLiveData()
    val progressBarIndicatorDone: LiveData<Boolean> = progressBarIndicator

    fun getCountryByRegion(region: String) {
        viewModelScope.launch(Dispatchers.IO) {
            progressBarIndicator.postValue(true)
            val response: Resource<ArrayList<CountryClass>> =
                interactors.getCountriesByContinentUseCase(region)
            when (response) {
                is Resource.Error -> msg.postValue(response.message!!)
                is Resource.Success -> {
                    progressBarIndicator.postValue(false)
                    countries.postValue(response.data!!)
                }
            }
        }
    }
}