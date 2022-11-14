package com.example.examplemvvm3.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm3.data.model.QuoteModel
import com.example.examplemvvm3.domain.GetQuotesUseCase
import com.example.examplemvvm3.domain.GetRamdonQuoteUseCase
import com.example.examplemvvm3.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRamdonQuoteUseCase: GetRamdonQuoteUseCase
) : ViewModel() {

    private val quoteModel: MutableLiveData<Quote> = MutableLiveData<Quote>()
    val quoteModelLiveData: LiveData<Quote> get() = quoteModel

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var isLoadingLiveData: LiveData<Boolean> = isLoading

    fun onCreate() {
        isLoading.value = true
        viewModelScope.launch {
            val result: List<Quote>? = getQuotesUseCase()
            if (!result.isNullOrEmpty()) {
                isLoading.value = false
                quoteModel.value = result[0]
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            isLoading.value = true
            val quote = getRamdonQuoteUseCase()
            if (quote != null) {
                quoteModel.value = quote!!
            }
            isLoading.value = false
        }
    }

}