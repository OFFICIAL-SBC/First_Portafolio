package com.example.financesforyou.utils

sealed class Resource<T>(
    val message: String? = null,
    val data: T? =null
){
    class Success<T>(data:T): Resource<T>(data=data)

    class Error<T>( errorMessage: String): Resource<T>(message = errorMessage)

    class Loading<T>(data: T? = null) : Resource<T>(data=data)
}