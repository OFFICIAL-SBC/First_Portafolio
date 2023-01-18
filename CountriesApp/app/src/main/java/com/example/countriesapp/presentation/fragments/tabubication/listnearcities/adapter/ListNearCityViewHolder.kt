package com.example.countriesapp.presentation.fragments.tabubication.listnearcities.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countriesapp.databinding.ItemNearCityBinding
import com.example.countriesapp.domain.CityClass

class ListNearCityViewHolder(view: View) : ViewHolder(view) {

    private val binding = ItemNearCityBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(city: CityClass) {

        val lat = String.format("%.3f", city.latitude).toDouble()
        val lon = String.format("%.3f", city.longitude).toDouble()

        with(binding) {
            tvNameCity.text = city.name
            tvRegionCity.text = city.region
            city.distance?.let { tvDistanceCity.text = "Distance: ${it} kms" }
            tvLocationCity.text = "lat: $lat / lon: $lon"
            tvPopulationCity.text = "${city.population} inhabitants"
        }

    }

}