package com.example.financesforyou.framework

import com.example.financesforyou.data.FirebaseDataSourceCloudFirestore
import com.example.financesforyou.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FirebaseDataSourceCloudFirestoreImp(private val cloudDataBase: FirebaseFirestore):FirebaseDataSourceCloudFirestore {

    override suspend fun createNewUser(): Flow<Resource<Boolean>> {
        return flow{

        }
    }

}