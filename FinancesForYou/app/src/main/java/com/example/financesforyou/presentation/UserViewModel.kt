package com.example.financesforyou.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.financesforyou.domain.User
import com.example.financesforyou.framework.FinancesViewModel
import com.example.financesforyou.framework.Interactors
import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date

class UserViewModel(interactors: Interactors) : FinancesViewModel(interactors) {

    //User data
    private val userIndicator: MutableLiveData<User?> =
        MutableLiveData() // This will be the variable that contains user information.
    val userIndicatorDone: LiveData<User?> = userIndicator

    //RegisterIndicator
    private val _userRegistrationStatus: MutableLiveData<Resource<AuthResult>?> =
        MutableLiveData<Resource<AuthResult>?>()
    val userRegistrationStatus: LiveData<Resource<AuthResult>?> = _userRegistrationStatus

    //Login Indicator
    private val _userSignInStatus: MutableLiveData<Resource<AuthResult>?> =
        MutableLiveData<Resource<AuthResult>?>()
    val userSignInStatus: LiveData<Resource<AuthResult>?> = _userSignInStatus

    init {
        //This is because without this block the transaction fragment will be created and it'll appear
        // just for a few seconds before you're send to the logging fragment (this is, of course, if there is not a user's session open)
        setNullUser()
    }

    fun setNullUser() {
        userIndicator.value = null
    }

    fun setLiveDataToNull() {
        _userRegistrationStatus.value = null
        _userSignInStatus.value = null
    }

    fun getNameUser(): String {
        return userIndicator.value?.name!!
    }


    fun setUserData(_user: User) {
        userIndicator.value = _user
    }

    fun openSesion(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _userSignInStatus.postValue(Resource.Loading())
            val result: Resource<AuthResult> = interactors.signInUseCase(email, password)
            _userSignInStatus.postValue(result)
        }
    }

    fun createNewUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _userRegistrationStatus.postValue(Resource.Loading())
            val result = interactors.registerUseCase(email, password)
            _userRegistrationStatus.postValue(result)
        }
    }

    fun createNewUserInCloudFireStore(): LiveData<Resource<Boolean>> {
        return liveData(Dispatchers.IO) {
            interactors.createNewUserInCloudFireStore(userIndicator.value!!).collect {
                emit(it)
            }
        }
    }

    fun getUserFromCloudFireastore(uid: String): LiveData<Resource<User>> {
        return liveData(Dispatchers.IO) {
            interactors.getUserUseCase(uid).collect {
                emit(it)
            }
        }
    }

    fun getAuthState(): LiveData<String> {
        return liveData(Dispatchers.IO) {
            interactors.getAuthState().collect {
                emit(it)
            }
        }
    }

    fun signOut(): LiveData<Resource<Boolean>> = liveData(Dispatchers.IO) {
        interactors.signOutUseCase().collect {
            emit(it)
        }
    }


}