package com.example.countriesapp.presentation.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R
import com.example.countriesapp.domain.CountryClass

class CountryListAdapter(private var countryList: ArrayList<CountryClass>): RecyclerView.Adapter<CountryListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CountryListViewHolder(layoutInflater.inflate(R.layout.item_country,parent,false))
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        val item = countryList[position]
        holder.render(item)
    }

    override fun getItemCount() = countryList.size

    fun appendItems(newArray: ArrayList<CountryClass>){
        countryList.clear()
        countryList.addAll(newArray)
        notifyDataSetChanged()
    }
}