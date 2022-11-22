package com.example.weatherapp.framework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object WeatherViewModelFactory:ViewModelProvider.Factory {
    lateinit var dependencies: Interactors

    fun inject(dependencies: Interactors){
        WeatherViewModelFactory.dependencies=dependencies
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (WeatherViewModel::class.java.isAssignableFrom(modelClass)){
            return modelClass.getConstructor(Interactors::class.java)
                .newInstance(
                    dependencies
                )
        }else{
            throw IllegalStateException("ViewModel must extend WeatherViewModel")
        }
    }

}