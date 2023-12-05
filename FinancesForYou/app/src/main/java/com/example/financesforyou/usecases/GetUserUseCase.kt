package com.example.financesforyou.usecases

import com.example.financesforyou.data.FirebaseRepository
import com.example.financesforyou.domain.User
import com.example.financesforyou.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: FirebaseRepository
){

    suspend operator fun invoke (id: String): Flow<Resource<User>>{
        return repository.getUser(id)
    }

}