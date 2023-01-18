package com.example.countriesapp.presentation.fragments.tabubication.listnearcities

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.domain.CityClass
import com.example.countriesapp.domain.CityList
import com.example.countriesapp.framework.CountryViewModel
import com.example.countriesapp.framework.Interactors
import com.example.countriesapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListNearCityViewModel(interactors: Interactors) : CountryViewModel(interactors) {

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val listCity: MutableLiveData<ArrayList<CityClass>> = MutableLiveData()
    val listCityDone: LiveData<ArrayList<CityClass>> = listCity

    fun getCitiesNearToYou(location: String){
        viewModelScope.launch(Dispatchers.IO){
            val result: Resource<CityList> = interactors.getCityNearToALocationUseCase(location,100)
            when(result){
                is Resource.Error -> msg.postValue(result.message!!)
                is Resource.Success -> result.data?.let { listCity.postValue(it.data) }
            }
        }
    }

}