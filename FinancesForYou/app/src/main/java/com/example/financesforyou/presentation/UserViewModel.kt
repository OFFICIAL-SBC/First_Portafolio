package com.example.financesforyou.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financesforyou.framework.FinancesViewModel
import com.example.financesforyou.framework.Interactors
import com.google.firebase.auth.FirebaseAuth

class UserViewModel(interactors: Interactors): FinancesViewModel(interactors) {

    private lateinit var auth: FirebaseAuth

    private val userIndicator:MutableLiveData<Boolean> = MutableLiveData() // This will be the variable that contains user information.
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
                    msg = if (!result) task.exception.toString()
                    else "Welcome"
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
                    msg = if(result) "User registation has been made correctly."
                    else task.exception.toString()
                }
        }
        return registerIndicator
    }

    fun returnMessage():String{
        return msg
    }
}