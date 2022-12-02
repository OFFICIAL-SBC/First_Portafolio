package com.example.weatherapp.data

import android.util.Log
import com.example.weatherapp.domain.CustomErrorApi
import com.example.weatherapp.utils.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
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
                    // parsing api's own custom json error
                    // response in CustomErrorApi pojo

                    //Log.w("Hello",response.errorBody()?.string() ?: "Es nulo")

                    val errorResponse: CustomErrorApi? = convertErrorBody(response.errorBody()?.string()!!)

                    // Simply returning api's own failure message

                    Resource.Error(
                        errorMessage = errorResponse?.message
                            ?: "Something went wrong from custom error api"
                    )
                }

            } catch (e: HttpException) {
                // Returning HttpException's message
                // wrapped in Resource.Error
                Resource.Error(errorMessage = e.message() ?: "Something went wrong. HttpException")
            } catch (e: IOException) {
                // Returning no internet message
                // wrapped in Resource.Error
                Resource.Error(errorMessage = "There is not INTERNET conexion")
            } catch (e: Exception) {
                // Returning 'Something went wrong' in case
                // of unknown error wrapped in Resource.Error
                Resource.Error(errorMessage = "Something went wrong. Unknown error wrapped")
            }
        }


    }

    private fun convertErrorBody(errorBody: String): CustomErrorApi? {
        return try {
            val gson = Gson()
            val type = object : TypeToken<CustomErrorApi>() {}.type
            gson.fromJson(errorBody,type)
        } catch (exception: Exception) {
            null
        }
    }
}