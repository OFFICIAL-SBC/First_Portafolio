package com.example.countriesapp.domain

import java.io.Serializable

data class CountryItemClass(
    val cca2: String,
    val flags: Flags,
    val name: Name,
    val subregion: String
): Serializable