package com.example.weatherapp.presentation.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.framework.Interactors
import com.example.weatherapp.framework.WeatherViewModel
import com.example.weatherapp.presentation.Model.WeatherDataPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowWeatherViewModel(interactors: Interactors):WeatherViewModel(interactors) {

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    fun saveWeather(currentWeather: WeatherDataPresentation){
        viewModelScope.launch(Dispatchers.IO){
            interactors.saveCurrentWeatherUseCase(currentWeather)
            msg.postValue("Current weather location has been successfully added")
        }
    }


}