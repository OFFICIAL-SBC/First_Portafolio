package com.example.financesforyou.framework

import com.example.financesforyou.usecases.CreateNewUserInCloudFireStore
import com.example.financesforyou.usecases.GetAuthStateUseCase
import com.example.financesforyou.usecases.GetUserUseCase
import com.example.financesforyou.usecases.RegisterUseCase
import com.example.financesforyou.usecases.SignInUseCase
import com.example.financesforyou.usecases.SignOutUseCase

data class Interactors(
    val signInUseCase: SignInUseCase,
    val registerUseCase: RegisterUseCase,
    val createNewUserInCloudFireStore: CreateNewUserInCloudFireStore,
    val getUserUseCase: GetUserUseCase,
    val getAuthState: GetAuthStateUseCase,
    val signOutUseCase: SignOutUseCase
)