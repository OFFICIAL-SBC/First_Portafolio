package com.example.financesforyou.usecases

import com.example.financesforyou.data.FirebaseRepository
import com.example.financesforyou.utils.Resource
import kotlinx.coroutines.flow.Flow

class SignOutUseCase(private val repository: FirebaseRepository) {

    suspend operator fun invoke(): Flow<Resource<Boolean>>{
        return repository.signOut()
    }

}