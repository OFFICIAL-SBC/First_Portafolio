package com.example.financesforyou.data

import com.example.financesforyou.utils.Resource

interface FirebaseDataSourceAuth {

    suspend fun logIn(user: String, password: String):Resource<Boolean>
    suspend fun register(user: String, password: String):Resource<Boolean>

}