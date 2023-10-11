package com.example.financesforyou.framework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object FinanceViewModelFactory : ViewModelProvider.Factory {

    private lateinit var interactors: Interactors

    fun inject(depencies: Interactors) {
        FinanceViewModelFactory.interactors = depencies
    }

    // override fun <T : ViewModel> create(modelClass: Class<T>): T -> It is responsible for creating our view model's instance
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (FinancesViewModel::class.java.isAssignableFrom(modelClass)) {
            // modelClass.getConstructor(Interactors::class.java)
            //Gets the view-models constructor which has a Interactor and create
            //the instance of our view-models by calling newInstance
            return modelClass.getConstructor(Interactors::class.java)
                .newInstance(
                    interactors
                )
        } else {
            throw IllegalStateException("ViewModel must extend FiancesViewModel")
        }
    }

}