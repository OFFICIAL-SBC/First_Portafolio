package com.example.examplemvvm3.domain

import com.example.examplemvvm3.data.QuoteRepository
import com.example.examplemvvm3.data.model.QuoteModel
import com.example.examplemvvm3.domain.model.Quote
import javax.inject.Inject

class GetRamdonQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
){
    suspend operator fun invoke(): Quote?{
        val quotes= repository.getAllQuotesFromDatabase()

        return if(!quotes.isNullOrEmpty()){
            val randomNumber=(quotes.indices).random()
            quotes[randomNumber]
        } else null
    }
}