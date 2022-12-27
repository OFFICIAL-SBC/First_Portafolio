package com.example.countriesapp.presentation.fragments.detail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countriesapp.databinding.ItemCityBinding
import com.example.countriesapp.domain.CityClass

class CountryDetailViewHolder(view: View):ViewHolder(view) {

    private val binding = ItemCityBinding.bind(view)

    fun render(cityClass: CityClass){
        with(binding){
            this.tvCityName.text = cityClass.name
            this.tvCityRegion.text = cityClass.region.replace("Department","")
            this.tvnCityPopulation.text = cityClass.population.toString()
        }
    }

}