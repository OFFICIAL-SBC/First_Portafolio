package com.example.countriesapp.domain

data class CountryClass(
    val area: Double,
    val borders: List<String>?,
    val capital: List<String>,
    val capitalInfo: CapitalInfo,
    val cca2: String,
    val cca3: String,
    val ccn3: String,
    val cioc: String,
    val currencies: Map<String,Currency>,
    val demonyms: Demonyms,
    val flags: Flags,
    val idd: Idd,
    val languages: Map<String,String>,
    val latlng: List<Double>,
    val name: Name,
    val population: Int,
    val region: String,
    val subregion: String,
    val timezones: List<String>
)