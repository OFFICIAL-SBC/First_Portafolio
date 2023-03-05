package com.example.countriesapp.presentation.fragments.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R
import com.example.countriesapp.domain.CountryItemClass
import com.example.countriesapp.framework.local.room.CountryEntity

class FavoriteAdapter(private val placesList:ArrayList<CountryEntity>):RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoriteViewHolder(layoutInflater.inflate(R.layout.item_saved_place,parent,false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = placesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return placesList.size
    }

    fun appendItems(newArray: ArrayList<CountryEntity>) {
        placesList.clear()
        placesList.addAll(newArray)
        notifyDataSetChanged()
    }

}