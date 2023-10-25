package com.example.financesforyou.framework

import android.util.Log
import com.example.financesforyou.data.FirebaseDataSourceAuth
import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class FirebaseDataSourceAuthImpl:FirebaseDataSourceAuth {

    //Here I'm not using dependency injection. What should I do here?
    private val auth = FirebaseAuth.getInstance()
    override suspend fun logIn(user: String, password: String): Resource<AuthResult> {
        //It is suppose that we have to send the data through a flow.
        return try {
            //Here I have the user information, I'm not using it, Could I somehow send the user name to the welcome message
            // in the UserViewModel
            val result: AuthResult = auth.signInWithEmailAndPassword(user,password).await()
            Resource.Success(result)
        }catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun register(user: String, password: String): Resource<AuthResult> {
        return try {
            val result = auth.createUserWithEmailAndPassword(user,password).await()
            Resource.Success(result)
        }catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }
}