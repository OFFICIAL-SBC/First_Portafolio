package com.example.financesforyou.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financesforyou.framework.FinancesViewModel
import com.example.financesforyou.framework.Interactors
import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(interactors: Interactors): FinancesViewModel(interactors) {

    //User data
    private val userIndicator:MutableLiveData<Boolean> = MutableLiveData() // This will be the variable that contains user information.
    val userIndicatorDone:LiveData<Boolean> = userIndicator

    //LogIn indicator
    private val logInIndicator:MutableLiveData<Boolean> = MutableLiveData()
    val logInIndicatorDone:LiveData<Boolean> = logInIndicator

    //Register indicator
    private val registerIndicator:MutableLiveData<Boolean> = MutableLiveData()
    val registerIndicatorDone:LiveData<Boolean> = registerIndicator

    fun firstMoment(){
        userIndicator.value = false
    }


    private lateinit var msg: String

    fun openSesion(email:String, password: String){
        viewModelScope.launch(Dispatchers.IO){
            val result: Resource<Boolean> = interactors.signInUseCase(email,password)
            when(result){
                is Resource.Error -> {
                    msg = result.message!!
                    logInIndicator.postValue(false)
                }
                is Resource.Success -> {
                    msg = "Welcome"
                    logInIndicator.postValue(true)
                }
            }
        }
    }

    fun createNewUser(email:String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactors.registerUseCase(email, password)
            when (result) {
                is Resource.Error -> {
                    msg = result.message!!
                    registerIndicator.postValue(false)
                }

                is Resource.Success -> {
                    msg = "The user has been registered"
                    registerIndicator.postValue(true)
                }
            }
        }
    }

    fun returnMessage(): String{
        return msg
    }

}