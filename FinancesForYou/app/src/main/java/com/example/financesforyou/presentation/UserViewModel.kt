package com.example.financesforyou.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financesforyou.framework.FinancesViewModel
import com.example.financesforyou.framework.Interactors
import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(interactors: Interactors): FinancesViewModel(interactors) {

    //User data
    private val userIndicator:MutableLiveData<Boolean> = MutableLiveData() // This will be the variable that contains user information.
    val userIndicatorDone:LiveData<Boolean> = userIndicator

    //RegisterIndicator
    private val _userRegistrationStatus: MutableLiveData<Resource<AuthResult>?> = MutableLiveData<Resource<AuthResult>?>()
    val userRegistrationStatus: LiveData<Resource<AuthResult>?> = _userRegistrationStatus

    //Login Indicator
    private val _userSignInStatus: MutableLiveData<Resource<AuthResult>?> = MutableLiveData<Resource<AuthResult>?>()
    val userSignInStatus: LiveData<Resource<AuthResult>?> = _userSignInStatus


    //This function represent the actual user data, I'll change them later
    //--------------------------------------------------------------------
    fun firstMoment(){
        userIndicator.value = false
    }

    fun secondMoment(){
        userIndicator.value = true
    }
    //--------------------------------------------------------------------

    fun openSesion(email:String, password: String){
        viewModelScope.launch(Dispatchers.IO){
            _userSignInStatus.postValue(Resource.Loading())
            val result: Resource<AuthResult> = interactors.signInUseCase(email,password)
            _userSignInStatus.postValue(result)
        }
    }

    fun createNewUser(email:String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _userRegistrationStatus.postValue(Resource.Loading())
            val result = interactors.registerUseCase(email, password)
            _userRegistrationStatus.postValue(result)
        }
    }

    fun setLiveDataToNull(){
        _userRegistrationStatus.value = null
        _userSignInStatus.value = null
    }

}