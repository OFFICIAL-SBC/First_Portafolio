package com.example.weatherapp.data


import com.example.weatherapp.domain.WeatherDataDomain
import com.example.weatherapp.presentation.Model.WeatherDataPresentation
import com.example.weatherapp.presentation.Model.toPresentation
import com.example.weatherapp.utils.Resource
import com.google.android.gms.maps.model.LatLng

class WeatherApiRepository(private val apiSource: WeatherDataSourceApi): BaseRepo() {

    suspend fun getCurrentWeather(location: LatLng): Resource<WeatherDataPresentation>{
        val result: Resource<WeatherDataDomain> = safeApiCall { apiSource.getCurrentWeather(location) }

        return when(result){
            is Resource.Error -> Resource.Error(errorMessage = result.message ?: "Somthing wnet wrong")
            is Resource.Success -> Resource.Success(data = result.data?.toPresentation()!!)
        }

    }


}