package com.example.financesforyou.usecases

import com.example.financesforyou.data.FirebaseRepository
import com.example.financesforyou.domain.User
import com.example.financesforyou.utils.Resource

import kotlinx.coroutines.flow.Flow

class CreateNewUserInCloudFireStore(private val repository: FirebaseRepository) {

    suspend operator fun invoke(user: User): Flow<Resource<Boolean>> {
        return repository.addNewUserCloudFiresatore(user)
    }

}