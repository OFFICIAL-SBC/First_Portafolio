package com.example.countriesapp.framework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object CountryViewModelFactory: ViewModelProvider.Factory {

    lateinit var dependencies: Interactors

    fun inject(dependencies: Interactors){
        CountryViewModelFactory.dependencies=dependencies
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
         if(CountryViewModel::class.java.isAssignableFrom(modelClass)){
             return modelClass.getConstructor(Interactors::class.java)
                 .newInstance(
                     dependencies
                 )
        }else{
            throw IllegalStateException("ViewModel must extend CountryViewModel")
        }
    }

}