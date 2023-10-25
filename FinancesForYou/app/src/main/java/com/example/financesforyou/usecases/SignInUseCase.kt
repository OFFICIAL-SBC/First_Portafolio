package com.example.financesforyou.usecases

import com.example.financesforyou.data.FirebaseRepository
import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.AuthResult

class SignInUseCase(private val firebaseRepository: FirebaseRepository) {

    suspend operator fun invoke (user:String, password: String): Resource<AuthResult> {
        return firebaseRepository.signIn(user,password)
    }

}