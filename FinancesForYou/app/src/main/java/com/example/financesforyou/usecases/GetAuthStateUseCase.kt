package com.example.financesforyou.usecases

import com.example.financesforyou.data.FirebaseRepository
import kotlinx.coroutines.flow.Flow

class GetAuthStateUseCase(private val repository: FirebaseRepository) {
    suspend operator fun invoke(): Flow<String>{
        return repository.getAuthStateUser()
    }

}