package com.example.financesforyou.framework

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

object FinanceViewModelFactory : ViewModelProvider.Factory {

   lateinit var dependencies: Interactors

    fun inject(dependencies: Interactors) {
        FinanceViewModelFactory.dependencies = dependencies
    }

    // override fun <T : ViewModel> create(modelClass: Class<T>): T -> It is responsible for creating our view model's instance
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (FinancesViewModel::class.java.isAssignableFrom(modelClass)) { // I always have to do this if, that's why we create
                                                                         // a parent class view-model, otherwise, every single viewmodel woodl have to have its own Factory
            // modelClass.getConstructor(Interactors::class.java)
            //Gets the view-models constructor which has a Interactor and create
            //the instance of our view-models by calling newInstance
            return modelClass.getConstructor(Interactors::class.java)
                .newInstance(
                    dependencies
                )
        } else {
            throw IllegalStateException("ViewModel must extend FiancesViewModel")
        }
    }

}