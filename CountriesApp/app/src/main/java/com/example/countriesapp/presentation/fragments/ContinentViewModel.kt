package com.example.countriesapp.presentation.fragments


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.domain.CountryList
import com.example.countriesapp.framework.CountryViewModel
import com.example.countriesapp.framework.Interactors
import com.example.countriesapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContinentViewModel(interactors: Interactors) : CountryViewModel(interactors){

     fun getCountryByCapital(capital: String){
        viewModelScope.launch(Dispatchers.IO){
            val response: Resource<CountryList> =interactors.getCountryByCapitalUseCase(capital)
            when(response){
                is Resource.Error -> Log.e("HELLO ERROR",response.message!!)
                is Resource.Success -> Log.w("HELLO RESULT", response.data.toString())
            }
        }
    }

}