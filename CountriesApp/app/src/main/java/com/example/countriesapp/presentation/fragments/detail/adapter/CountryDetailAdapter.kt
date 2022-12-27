package com.example.countriesapp.presentation.fragments.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R
import com.example.countriesapp.domain.CityClass
import com.example.countriesapp.domain.CountryItemClass

class CountryDetailAdapter(private val cityList: ArrayList<CityClass>) :
    RecyclerView.Adapter<CountryDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CountryDetailViewHolder(layoutInflater.inflate(R.layout.item_city,parent,false))
    }

    override fun onBindViewHolder(holder: CountryDetailViewHolder, position: Int) {
        holder.render(cityList[position])
    }

    override fun getItemCount(): Int = cityList.size

    fun appendItems(newArray: ArrayList<CityClass>){
        cityList.clear()
        cityList.addAll(newArray)
        notifyDataSetChanged()
    }
}