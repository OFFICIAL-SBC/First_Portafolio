package com.example.financesforyou.framework

import com.example.financesforyou.data.FirebaseDataSourceCloudFirestore
import com.example.financesforyou.domain.User
import com.example.financesforyou.utils.Resource
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


class FirebaseDataSourceCloudFirestoreImp(private val cloudDataBase: FirebaseFirestore) :
    FirebaseDataSourceCloudFirestore {

    //I tried to use a flow, catch blocks
    //flow{
    // ..
    // }.catch { I tried to do it this way but an arror appered: here in the catch block I had to emit Resource.Success(User) which doesn't make sense.
    //..
    //}

    override suspend fun createNewUser(user: User): Flow<Resource<Boolean>> {
        return flow {
            try {
                cloudDataBase.collection("users").document(user.id!!).set(user).await()
                    .also { emit(Resource.Success(true)) }
            } catch (e: Exception) {
                emit(Resource.Error(e.message!!))
            }
        }
    }

    override suspend fun getUser(id: String): Flow<Resource<User>> = flow {
        try {
            //Im creating user22 to see what the type of the result of cloudDataBase.collection("users").document(id).get().await() is
            val user22: DocumentSnapshot =
                cloudDataBase.collection("users").document(id).get().await()
            val userObject = user22.toObject(User::class.java)

            userObject?.let { result ->
                emit(Resource.Success(result))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message!!))
        }
    }

}