package com.example.weatherapp.presentation.fragments


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.framework.Interactors
import com.example.weatherapp.framework.WeatherViewModel
import com.example.weatherapp.presentation.Model.WeahterDataPresentation
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapViewModel(interactors: Interactors) : WeatherViewModel(interactors) {

    private val msg:MutableLiveData<String> = MutableLiveData()
    val msgDone:LiveData<String> = msg

    fun getCurrentWeather(ubication: LatLng){
        viewModelScope.launch(Dispatchers.IO){
            val result: WeahterDataPresentation =interactors.getCurrentWeatherUseCase(ubication)
            Log.w("HELLO",result.toString())
        }
    }

}