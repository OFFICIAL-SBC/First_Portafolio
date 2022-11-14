package com.example.examplemvvm3.data.network

import com.example.examplemvvm3.core.RetrofitHelper
import com.example.examplemvvm3.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class QuoteService @Inject constructor(
    private val api: QuoteApiClient
){
    suspend fun getQuotes():List<QuoteModel>{
        return withContext(Dispatchers.IO){
            val response=api.getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}