package com.example.countriesapp.presentation.fragments.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countriesapp.databinding.ItemCountryBinding
import com.example.countriesapp.domain.CountryItemClass
import com.squareup.picasso.Picasso

class CountryListViewHolder(view: View):ViewHolder(view) {

    private val binding = ItemCountryBinding.bind(view)

    fun render(currentCountry: CountryItemClass, onItemClicked: (CountryItemClass) -> Unit){

        with(binding){
            Picasso.get()
                .load(currentCountry.flags.png)
                .into(ivFlag)
            tvNameCountry.text = currentCountry.name.common
            tvNameRegion.text = currentCountry.subregion
            ivFlag.setOnClickListener {
                onItemClicked(currentCountry)
            }
        }

    }


}