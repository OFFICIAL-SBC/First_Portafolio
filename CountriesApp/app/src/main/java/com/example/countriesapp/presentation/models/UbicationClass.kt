package com.example.countriesapp.presentation.models

import java.io.Serializable

data class UbicationClass(

    val latitud: Double,
    val longitud: Double,
    val address: String,
    val countryName: String,
    val locality: String

): Serializable