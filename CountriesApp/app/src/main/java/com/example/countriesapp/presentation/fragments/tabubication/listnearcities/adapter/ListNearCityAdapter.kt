package com.example.countriesapp.presentation.fragments.tabubication.listnearcities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R
import com.example.countriesapp.domain.CityClass

class ListNearCityAdapter(private val nearCities: ArrayList<CityClass>): RecyclerView.Adapter<ListNearCityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNearCityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListNearCityViewHolder(layoutInflater.inflate(R.layout.item_near_city,parent,false))
    }

    override fun onBindViewHolder(holder: ListNearCityViewHolder, position: Int) {
        val item = nearCities[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = nearCities.size

    fun addCities(new: ArrayList<CityClass>){
        nearCities.clear()
        nearCities.addAll(new)
        notifyDataSetChanged()
    }

}