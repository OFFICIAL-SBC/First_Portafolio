package com.example.countriesapp.data

import com.example.countriesapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepo() {
    //We use this function in all repos to handle api errors
    suspend fun <T> safeApiCall(apiToBeCall: suspend () -> Response<T>): Resource<T> {

        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCall()

                if (response.isSuccessful) {
                    // In case of success response we
                    // are returning Resource.Success object
                    // by passing our data in it.
                    Resource.Success(data = response.body()!!)
                } else {
                    val errorMessage = response.errorBody()?.string() //You only can use this line once.
                        ?: "Something went wrong. response.errorBody()?.string()"
                    Resource.Error(errorMessage = errorMessage)
                }
            } catch (e: HttpException) {
                // Returning HttpException's message
                // wrapped in Resource.Error
                Resource.Error(errorMessage = e.message() ?: "Something went wrong. HttpException")
            } catch (e: IOException) {
                // Returning no internet message
                // wrapped in Resource.Error
                Resource.Error(errorMessage = e.message ?: "There is not internet conexion")
            } catch (e: Exception) {
                // Returning 'Something went wrong' in case
                // of unknown error wrapped in Resource.Error
                Resource.Error(errorMessage = e.message ?: "Unknown error")
            }
        }
    }

}