package com.example.financesforyou.framework

import com.example.financesforyou.data.FirebaseDataSourceAuth
import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class FirebaseDataSourceAuthImpl:FirebaseDataSourceAuth {

    //Here I'm not using dependency injection. What should I do here?
    private val auth = FirebaseAuth.getInstance()
    override suspend fun logIn(user: String, password: String): Resource<Boolean> {
        //It is suppose that we have to send the data through a flow.
        return try {
            val result = auth.signInWithEmailAndPassword(user,password).await()
            Resource.Success(true)
        }catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun register(user: String, password: String): Resource<Boolean> {
        var msg: String
        return try {
            val result = auth.createUserWithEmailAndPassword(user,password).await()
            Resource.Success(true)
        }catch (e: Exception){
            Resource.Error(e.message.toString())
        }
    }
}