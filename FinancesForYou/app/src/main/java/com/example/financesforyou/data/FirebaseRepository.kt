package com.example.financesforyou.data

import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

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

    suspend fun addNewUserCloudFiresatore(): Flow<Resource<Boolean>> {
        return firebaseDataSourceCloudFirestore.createNewUser()
    }


}