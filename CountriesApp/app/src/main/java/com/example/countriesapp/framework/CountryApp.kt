package com.example.countriesapp.framework

import android.app.Application
import com.example.countriesapp.data.CountryApiRepository
import com.example.countriesapp.usecases.*

class CountryApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val countryApiRepository =
            CountryApiRepository(CountryDataSourceOneImpl(), CountryDataSourceTwoImpl())

        val interactors = Interactors(
            GetCountriesByContinentUseCase(countryApiRepository),
            GetCountryByCapitalUseCase(countryApiRepository),
            GetCitiesByCountryUseCase(countryApiRepository),
            GetCountryByCodeUseCase(countryApiRepository),
            GetCityNearToALocationUseCase(countryApiRepository)
        )

        CountryViewModelFactory.inject(dependencies = interactors)

    }
}