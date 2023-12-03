package com.example.financesforyou.usecases

import com.example.financesforyou.data.FirebaseRepository
import com.example.financesforyou.domain.User
import kotlinx.coroutines.flow.Flow

class GetAuthStateUseCase(private val repository: FirebaseRepository) {
    suspend operator fun invoke(): Flow<User?>{
        return repository.getAuthStateUser()
    }

}