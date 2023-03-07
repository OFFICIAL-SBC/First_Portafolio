package com.example.countriesapp.presentation.fragments.favorites.adapter

import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countriesapp.databinding.ItemSavedPlaceBinding
import com.example.countriesapp.framework.local.room.CountryEntity

class FavoriteViewHolder(view: View):ViewHolder(view) {

    private val binding = ItemSavedPlaceBinding.bind(view)

    fun render(entity: CountryEntity){

        with(binding){
            if(entity.photoPath != ""){
                val bitmap = BitmapFactory.decodeFile(entity.photoPath)
                ivSavedPlace.setImageBitmap(bitmap)
            }
            tvDate.text = entity.date
            tvLocation.text = entity.address
        }
    }


}