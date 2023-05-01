package com.example.financesforyou.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class UserViewModel: ViewModel() {

    private lateinit var auth: FirebaseAuth

    private val userIndicator:MutableLiveData<Boolean> = MutableLiveData() // This will the variable that contains user information.
    val userIndicatorDone:LiveData<Boolean> = userIndicator

    private var msg: String = ""


    fun firstMoment(){
        userIndicator.value = false
    }
    fun initializeAuth(){
        auth = FirebaseAuth.getInstance()
    }

    fun openSesion(email:String, password: String):LiveData<Boolean>{
        val loginIndicator:MutableLiveData<Boolean> = MutableLiveData()
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(){ task ->
                loginIndicator.value = task.isSuccessful.also { result ->
                    if (!result) msg = task.exception.toString()
                    else msg = "Welcome"
                }
                userIndicator.value = task.isSuccessful //This is just temporary. This will be a class that will hold all user information.
            }
        return loginIndicator
    }

    fun createNewUser(email:String, password: String):LiveData<Boolean>{
        Log.i("AQUITOY","Hello")
        val registerIndicator: MutableLiveData<Boolean> = MutableLiveData()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(){ task ->
                registerIndicator.value = task.isSuccessful.also { result ->
                    if(result) msg = "User registation has been made correctly."
                    else msg = task.exception.toString()
                }
        }
        return registerIndicator
    }

    fun returnMessage():String{
        return msg
    }
}