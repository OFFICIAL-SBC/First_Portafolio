package com.example.countriesapp.presentation.fragments.capital.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countriesapp.databinding.ItemCapitalBinding

class CapitalViewHolder(view: View): ViewHolder(view)  {

    private val binding = ItemCapitalBinding.bind(view)

    fun bind(capital: String, onItemClicked: (String) -> Unit){
        binding.tvTitleCapital.text = capital

        binding.tvTitleCapital.setOnClickListener {
            onItemClicked(capital)
        }

    }

}