package com.example.financesforyou.data

import com.example.financesforyou.domain.User
import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface FirebaseDataSourceAuth {

    suspend fun logIn(user: String, password: String):Resource<AuthResult>
    suspend fun register(user: String, password: String):Resource<AuthResult>
    suspend fun getAuthState(): Flow<User?>
    suspend fun signOut(): Flow<Resource<Boolean>>
}