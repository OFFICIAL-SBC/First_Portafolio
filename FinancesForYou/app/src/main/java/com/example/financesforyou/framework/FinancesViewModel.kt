package com.example.financesforyou.framework

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class FinancesViewModel @Inject constructor(
    protected val interactors: Interactors
):ViewModel()

//In Kotlin, the protected modifier is used to restrict the visibility of a member (such as a property or function) to its own class and its subclasses.
