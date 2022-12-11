package com.example.countriesapp.domain

data class Name(
    val common: String,
    val nativeName: Map<String,NativeName>,
    val official: String
)