package com.example.financesforyou.framework

import android.app.Application
import com.example.financesforyou.data.FirebaseRepository
import com.example.financesforyou.usecases.RegisterUseCase
import com.example.financesforyou.usecases.SignInUseCase

class FinancesApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val firebaseRepository= FirebaseRepository(FirebaseDataSourceAuthImpl())

        val interactors = Interactors(
                SignInUseCase(firebaseRepository),
                RegisterUseCase(firebaseRepository)
        )

        FinanceViewModelFactory.inject(interactors)

    }

}