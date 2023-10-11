package com.example.financesforyou.framework

import com.example.financesforyou.usecases.RegisterUseCase
import com.example.financesforyou.usecases.SignInUseCase

data class Interactors(
    val signInUseCase: SignInUseCase,
    val registerUseCase: RegisterUseCase
)