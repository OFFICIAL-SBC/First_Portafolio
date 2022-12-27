package com.example.countriesapp.presentation.fragments.continent


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.domain.CountryItemClass
import com.example.countriesapp.framework.CountryViewModel
import com.example.countriesapp.framework.Interactors
import com.example.countriesapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContinentViewModel(interactors: Interactors) : CountryViewModel(interactors) {

    private val countries: MutableLiveData<ArrayList<CountryClass>> = MutableLiveData()
    val countriesDone: LiveData<ArrayList<CountryClass>> = countries

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg



}