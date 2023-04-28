package com.example.financesforyou.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class UserViewModel: ViewModel() {

    private lateinit var auth: FirebaseAuth

    val userIndicator:MutableLiveData<Boolean> = MutableLiveData() // This will the variable that contains user information.
    private val userIndicatorDone = userIndicator

    fun firstMoment(){
        userIndicator.value = false
    }
    fun initializeAuth(){
        auth = FirebaseAuth.getInstance()
    }

    fun openSesion(email:String, password: String):LiveData<Boolean>{
        //What about Error messages?
        val loginIndicator:MutableLiveData<Boolean> = MutableLiveData()
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(){ task ->
                loginIndicator.value = task.isSuccessful
                userIndicator.value = task.isSuccessful //This is just temporary.
            }
        return loginIndicator
    }

}