package com.example.countriesapp.presentation.fragments.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.domain.CityClass

class CountryDetailAdapter(private val cityList: ArrayList<CityClass>):RecyclerView.Adapter<CountryDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDetailViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CountryDetailViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = cityList.size
}