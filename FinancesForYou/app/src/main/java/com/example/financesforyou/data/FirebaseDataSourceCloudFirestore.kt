package com.example.financesforyou.data

import com.example.financesforyou.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseDataSourceCloudFirestore {

    suspend fun createNewUser(): Flow<Resource<Boolean>>

}