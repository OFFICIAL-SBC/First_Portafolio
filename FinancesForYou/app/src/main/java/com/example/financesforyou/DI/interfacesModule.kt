package com.example.financesforyou.DI

import com.example.financesforyou.data.FirebaseDataSourceAuth
import com.example.financesforyou.data.FirebaseDataSourceCloudFirestore
import com.example.financesforyou.framework.FirebaseDataSourceAuthImpl
import com.example.financesforyou.framework.FirebaseDataSourceCloudFirestoreImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class interfacesModule {

    @Binds
    @Singleton
    abstract fun provideFirebaseDataSourceAuth(
       firebaseDataSourceAuthImpl: FirebaseDataSourceAuthImpl
    ):FirebaseDataSourceAuth

    @Binds
    @Singleton
    abstract fun provideFirebaseDataSourceCloudFireStore(
        firebaseDataSourceCloudFirestoreImp: FirebaseDataSourceCloudFirestoreImp
    ):FirebaseDataSourceCloudFirestore

}