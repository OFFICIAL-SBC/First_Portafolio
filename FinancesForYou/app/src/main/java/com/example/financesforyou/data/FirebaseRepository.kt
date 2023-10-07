package com.example.financesforyou.data

import com.example.financesforyou.utils.Resource

class FirebaseRepository(private val firebaseDataSourceAuth: FirebaseDataSourceAuth) {

    suspend fun signIn(email: String, password: String): Resource<Boolean> {
        return firebaseDataSourceAuth.logIn(email,password)
    }

    suspend fun newUser(email: String, password: String):Resource<Boolean>{
        return firebaseDataSourceAuth.register(email,password)
    }
}