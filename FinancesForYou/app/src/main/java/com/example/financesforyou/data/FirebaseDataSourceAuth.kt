package com.example.financesforyou.data

import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.AuthResult

interface FirebaseDataSourceAuth {

    suspend fun logIn(user: String, password: String):Resource<AuthResult>
    suspend fun register(user: String, password: String):Resource<AuthResult>

}