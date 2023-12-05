package com.example.financesforyou.usecases

import com.example.financesforyou.data.FirebaseRepository
import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke (email:String, password: String):Resource<AuthResult>{
        return firebaseRepository.newUser(email,password)
    }
}