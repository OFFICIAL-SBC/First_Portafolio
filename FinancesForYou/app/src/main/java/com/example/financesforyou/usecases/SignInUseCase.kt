package com.example.financesforyou.usecases

import com.example.financesforyou.data.FirebaseRepository
import com.example.financesforyou.utils.Resource

class SignInUseCase(private val firebaseRepository: FirebaseRepository) {

    suspend operator fun invoke (user:String, password: String): Resource<Boolean> {
        return firebaseRepository.signIn(user,password)
    }

}