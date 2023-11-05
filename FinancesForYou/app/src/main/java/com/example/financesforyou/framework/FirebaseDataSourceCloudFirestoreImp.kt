package com.example.financesforyou.framework

import com.example.financesforyou.data.FirebaseDataSourceCloudFirestore
import com.example.financesforyou.domain.User
import com.example.financesforyou.utils.Resource
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

}