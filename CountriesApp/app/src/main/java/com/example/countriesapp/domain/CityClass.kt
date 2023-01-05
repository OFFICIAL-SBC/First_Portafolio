package com.example.countriesapp.domain

data class CityClass(
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val population: Int,
    val distance: Double? = null
)