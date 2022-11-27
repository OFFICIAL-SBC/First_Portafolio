package com.example.weatherapp.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.framework.Interactors
import com.example.weatherapp.framework.WeatherViewModel
import com.example.weatherapp.presentation.Model.WeatherDataPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(interactors: Interactors) : WeatherViewModel(interactors){

    private val listLoaded: MutableLiveData<List<WeatherDataPresentation>> = MutableLiveData()
    val listLoadedDone : LiveData<List<WeatherDataPresentation>> = listLoaded

    private val indicator: MutableLiveData<Boolean> = MutableLiveData()
    val indicatorDone: LiveData<Boolean> = indicator

    fun getSavedWeatherObjects(){
        viewModelScope.launch(Dispatchers.IO){
            val result: List<WeatherDataPresentation> = interactors.getListSavedWeatherObjectsUseCase()
            listLoaded.postValue(result)
        }
    }

    fun deleteSelectedWeather(selectedWeather: WeatherDataPresentation){
        viewModelScope.launch(Dispatchers.IO){
            interactors.deleteSelectedWeatherUseCase(selectedWeather)
            indicator.postValue(true)
        }
    }

}