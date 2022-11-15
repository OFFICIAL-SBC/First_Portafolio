package com.example.examplemvvm3.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm3.domain.AddQuoteUseCase
import com.example.examplemvvm3.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddQuoteViewModel @Inject constructor(
    private val addQuoteUseCase: AddQuoteUseCase
) : ViewModel() {

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    fun addNewQuote(quote: String, author: String){
        if (!quote.isNullOrEmpty() and !author.isNullOrEmpty()){
            viewModelScope.launch {
                addQuoteUseCase(quote,author)
                msg.value="Your quote have been successfully added to our list"
            }
        }else{
            msg.value="Please type a new quote with its author"
        }
    }

}