package com.example.financesforyou.framework

import com.example.financesforyou.data.FirebaseDataSourceCloudFirestore
import com.example.financesforyou.domain.User
import com.example.financesforyou.utils.Resource
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


class FirebaseDataSourceCloudFirestoreImp(private val cloudDataBase: FirebaseFirestore) :
    FirebaseDataSourceCloudFirestore {

    override suspend fun createNewUser(user: User): Flow<Resource<Boolean>> {
        return flow {
            cloudDataBase.collection("users").document(user.id!!).set(user).await()
                .also { emit(Resource.Success(true)) }
        }
    }

    override suspend fun getUser(id: String): Flow<Resource<User>> = flow{
        //Im creating user to see what the type of the result of cloudDataBase.collection("users").document(id).get().await() is
        val user: DocumentSnapshot = cloudDataBase.collection("users").document(id).get().await()
        val userObject = user.toObject(User::class.java)
        //What if is there an error?
        userObject?.let {result ->
            emit(Resource.Success(result))
        }
    }

}