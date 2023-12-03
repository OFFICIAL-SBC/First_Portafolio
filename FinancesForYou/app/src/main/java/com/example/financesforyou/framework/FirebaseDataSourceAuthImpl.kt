package com.example.financesforyou.framework

import com.example.financesforyou.data.FirebaseDataSourceAuth
import com.example.financesforyou.domain.User
import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.Date
import kotlin.Exception

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

    override suspend fun getAuthState(): Flow<User?> {
        return callbackFlow {
            // This is the lambda version

            val authStateListener = AuthStateListener { auth ->
                val send = if (auth.currentUser == null) null
                else {
                    User(
                        id = auth.currentUser!!.uid,
                        name = auth.currentUser!!.displayName ?: "",
                        email = auth.currentUser!!.email,
                        photoUrl = auth.currentUser!!.photoUrl.toString(),
                        createdAt = Date(auth.currentUser!!.metadata!!.creationTimestamp)
                    )
                }
                trySend(send)
            }

            //This is the traditional way to do it.
//            val authStateListener = object : AuthStateListener {
//                override fun onAuthStateChanged(p0: FirebaseAuth) {
//                    val send = if (p0.currentUser == null) ""
//                     else p0.currentUser!!.uid
//                    trySend(send)
//                }
//
//            }

            //Here I'm making this flow a hot stream
            auth.addAuthStateListener(authStateListener)

            awaitClose {
                auth.removeAuthStateListener(authStateListener)
            }

        }
    }

    override suspend fun signOut(): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                auth.signOut()
                emit(Resource.Success(true))
            }catch (e: Exception){
                emit(Resource.Error(errorMessage = e.message!!))
            }
        }
    }
}