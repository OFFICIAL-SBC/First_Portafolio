package com.example.countriesapp.presentation.fragments.list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R
import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.domain.CountryItemClass

class CountryListAdapter(
    private var countryList: ArrayList<CountryItemClass>,
    private val onItemClicked: (CountryItemClass) -> Unit
) : RecyclerView.Adapter<CountryListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CountryListViewHolder(layoutInflater.inflate(R.layout.item_country, parent, false))
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        val item = countryList[position]
        holder.render(item,onItemClicked)
    }

    override fun getItemCount() = countryList.size

    fun appendItems(newArray: ArrayList<CountryItemClass>) {
        countryList.clear()
        countryList.addAll(newArray)
        notifyDataSetChanged()
    }
}