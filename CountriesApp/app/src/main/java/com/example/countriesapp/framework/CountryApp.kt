package com.example.countriesapp.framework

import android.app.Application
import com.example.countriesapp.data.CountryApiRepository
import com.example.countriesapp.usecases.GetCountriesByContinentUseCase
import com.example.countriesapp.usecases.getCountryByCapitalUseCase

class CountryApp:Application() {
    override fun onCreate() {
        super.onCreate()

        val countryApiRepository = CountryApiRepository(CountryDataSourceOneImpl())

        val interactors = Interactors(
            GetCountriesByContinentUseCase(countryApiRepository),
            getCountryByCapitalUseCase(countryApiRepository)
        )

        CountryViewModelFactory.inject(dependencies = interactors)

    }
}