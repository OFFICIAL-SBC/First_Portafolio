package com.example.weatherapp.framework.network

import com.example.weatherapp.domain.WeatherDataDomain
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class WeatherApiClient {

    private val retrofit = RetrofitHelper.getInstanceRetrofit()

    suspend fun getCurrentWeather(position: LatLng): Response<WeatherDataDomain> {
        val lon = position.longitude.toString()
        val lat = position.latitude.toString()
        return withContext(Dispatchers.IO){
            retrofit.create(APIService::class.java).getCurrentWeatherData(lon, lat)
        }
    }

}