package com.example.countriesapp.framework

import android.app.Application
import com.example.countriesapp.data.CountryApiRepository
import com.example.countriesapp.usecases.GetCitiesByCountryUseCase
import com.example.countriesapp.usecases.GetCountriesByContinentUseCase
import com.example.countriesapp.usecases.GetCountryByCapitalUseCase
import com.example.countriesapp.usecases.GetCountryByCodeUseCase

class CountryApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val countryApiRepository =
            CountryApiRepository(CountryDataSourceOneImpl(), CountryDataSourceTwoImpl())

        val interactors = Interactors(
            GetCountriesByContinentUseCase(countryApiRepository),
            GetCountryByCapitalUseCase(countryApiRepository),
            GetCitiesByCountryUseCase(countryApiRepository),
            GetCountryByCodeUseCase(countryApiRepository)
        )

        CountryViewModelFactory.inject(dependencies = interactors)

    }
}