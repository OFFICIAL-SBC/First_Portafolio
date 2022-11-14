package com.example.examplemvvm3.domain

import com.example.examplemvvm3.data.QuoteRepository
import com.example.examplemvvm3.data.model.toDataRepository
import com.example.examplemvvm3.domain.model.Quote
import javax.inject.Inject

class AddQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(quote: String, author: String){
        val newQuote: Quote = Quote(quote,author)
        repository.saveQuote(newQuote.toDataRepository())
    }
}