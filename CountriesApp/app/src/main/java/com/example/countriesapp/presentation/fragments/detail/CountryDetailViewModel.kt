package com.example.countriesapp.presentation.fragments.detail


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.domain.CityClass
import com.example.countriesapp.domain.CityList
import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.framework.CountryViewModel
import com.example.countriesapp.framework.Interactors
import com.example.countriesapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryDetailViewModel(interactors: Interactors) : CountryViewModel(interactors){

    private val cities: MutableLiveData<ArrayList<CityClass>> = MutableLiveData()
    val citiesDone: LiveData<ArrayList<CityClass>> = cities

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val country: MutableLiveData<CountryClass> = MutableLiveData()
    val countryDone: LiveData<CountryClass> = country

    fun getCitiesByCountry(countryId: String){
        viewModelScope.launch(Dispatchers.IO){
            val result: Resource<CityList> = interactors.getCitiesByCountryUseCase(countryId)
            when(result){
                is Resource.Error -> msg.postValue(result.message!!)
                is Resource.Success -> cities.postValue(result.data?.data)
            }
        }
    }

    fun getCountryByCode(code: String){
        viewModelScope.launch(Dispatchers.IO){
            val result: Resource<ArrayList<CountryClass>> = interactors.getCountryByCodeUseCase(code)
            when(result){
                is Resource.Error -> msg.postValue(result.message!!)
                is Resource.Success -> country.postValue(result.data?.get(0))

            }
        }
    }

}