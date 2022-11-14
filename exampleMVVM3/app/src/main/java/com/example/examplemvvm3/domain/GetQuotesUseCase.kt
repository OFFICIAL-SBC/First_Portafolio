package com.example.examplemvvm3.domain

import com.example.examplemvvm3.data.QuoteRepository
import com.example.examplemvvm3.data.database.entities.toDatabase
import com.example.examplemvvm3.data.model.QuoteModel
import com.example.examplemvvm3.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository:QuoteRepository
){
    suspend operator fun invoke():List<Quote>?{
        val quotes: List<Quote> = repository.getAllQuotesFromApi()
        return if (quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }
    }

}