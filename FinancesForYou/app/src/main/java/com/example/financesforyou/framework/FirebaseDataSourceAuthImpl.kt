package com.example.financesforyou.framework

import com.example.financesforyou.data.FirebaseDataSourceAuth
import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class FirebaseDataSourceAuthImpl(private val auth: FirebaseAuth) : FirebaseDataSourceAuth {

    override suspend fun logIn(user: String, password: String): Resource<AuthResult> {
        //It is suppose that we have to send the data through a flow.
        return try {
            val result: AuthResult = auth.signInWithEmailAndPassword(user, password).await()
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun register(user: String, password: String): Resource<AuthResult> {
        return try {
            val result = auth.createUserWithEmailAndPassword(user, password).await()
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getAuthState(): Flow<String> {
        return callbackFlow {
            val authStateListener = AuthStateListener {
                val send = if (auth.currentUser == null) ""
                else auth.currentUser!!.uid
                trySend(send)
            }

            //Here I'm making this flow a hot stream
            auth.addAuthStateListener(authStateListener)

            awaitClose {
                auth.removeAuthStateListener(authStateListener)
            }

        }
    }
}