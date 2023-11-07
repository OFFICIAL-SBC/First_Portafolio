package com.example.financesforyou.data

import com.example.financesforyou.domain.User
import com.example.financesforyou.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseDataSourceCloudFirestore {

    suspend fun createNewUser(user: User): Flow<Resource<Boolean>>
    suspend fun getUser(id: String): Flow<Resource<User>>


}