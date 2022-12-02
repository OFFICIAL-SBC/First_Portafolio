package com.example.weatherapp.presentation.fragments


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.framework.Interactors
import com.example.weatherapp.framework.WeatherViewModel
import com.example.weatherapp.presentation.Model.WeatherDataPresentation
import com.example.weatherapp.utils.Resource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapViewModel(interactors: Interactors) : WeatherViewModel(interactors) {

    private val msg:MutableLiveData<String> = MutableLiveData()
    val msgDone:LiveData<String> = msg

    private val currentWeather: MutableLiveData<WeatherDataPresentation> = MutableLiveData()
    val currentWeatherDone: LiveData<WeatherDataPresentation> = currentWeather

    fun getCurrentWeather(ubication: LatLng){
        viewModelScope.launch(Dispatchers.IO){
            val result: Resource<WeatherDataPresentation> =interactors.getCurrentWeatherUseCase(ubication)
            when(result){
                is Resource.Error -> msg.postValue(result.message ?: "Something went wrong im in map view model")
                is Resource.Success -> result.data?.let { currentWeather.postValue(it) }
            }

        }
    }

}