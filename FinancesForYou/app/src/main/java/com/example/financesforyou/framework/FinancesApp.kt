package com.example.financesforyou.framework

import android.app.Application
import com.example.financesforyou.data.FirebaseRepository
import com.example.financesforyou.usecases.CreateNewUserInCloudFireStore
import com.example.financesforyou.usecases.GetAuthStateUseCase
import com.example.financesforyou.usecases.GetUserUseCase
import com.example.financesforyou.usecases.RegisterUseCase
import com.example.financesforyou.usecases.SignInUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FinancesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val cloudDataBase: FirebaseFirestore = FirebaseFirestore.getInstance()

        val firebaseRepository = FirebaseRepository(
            FirebaseDataSourceAuthImpl(auth),
            FirebaseDataSourceCloudFirestoreImp(cloudDataBase)
        )

        val interactors = Interactors(
            SignInUseCase(firebaseRepository),
            RegisterUseCase(firebaseRepository),
            CreateNewUserInCloudFireStore(firebaseRepository),
            GetUserUseCase(firebaseRepository),
            GetAuthStateUseCase(firebaseRepository)
        )

        FinanceViewModelFactory.inject(dependencies = interactors)

    }

}