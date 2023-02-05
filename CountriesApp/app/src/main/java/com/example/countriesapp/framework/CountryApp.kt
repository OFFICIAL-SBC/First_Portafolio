package com.example.countriesapp.framework

import android.app.Application
import com.example.countriesapp.data.CountryApiRepository
import com.example.countriesapp.data.CountryRoomRepository
import com.example.countriesapp.usecases.*

class CountryApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val countryApiRepository =
            CountryApiRepository(CountryDataSourceOneImpl(), CountryDataSourceTwoImpl())
        val countryRoomRepository = CountryRoomRepository(CountryDataSourceDbImpl(this))

        val interactors = Interactors(
            GetCountriesByContinentUseCase(countryApiRepository),
            GetCountryByCapitalUseCase(countryApiRepository),
            GetCitiesByCountryUseCase(countryApiRepository),
            GetCountryByCodeUseCase(countryApiRepository),
            GetCityNearToALocationUseCase(countryApiRepository),
            GetAllSavedLocationsUseCase(countryRoomRepository),
            SaveCurrentLocationUseCase(countryRoomRepository),
            DeleteSelectedLocationUseCase(countryRoomRepository)
        )

        CountryViewModelFactory.inject(dependencies = interactors)
    }
}