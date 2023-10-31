package com.example.financesforyou.data

import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.AuthResult

class FirebaseRepository(
    private val firebaseDataSourceAuth: FirebaseDataSourceAuth,
    private val firebaseDataSourceCloudFirestore: FirebaseDataSourceCloudFirestore
    ) {

    suspend fun signIn(email: String, password: String): Resource<AuthResult> {
        return firebaseDataSourceAuth.logIn(email,password)
    }

    suspend fun newUser(email: String, password: String):Resource<AuthResult>{
        return firebaseDataSourceAuth.register(email,password)
    }


}