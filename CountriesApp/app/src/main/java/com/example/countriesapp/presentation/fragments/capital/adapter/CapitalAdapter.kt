package com.example.countriesapp.presentation.fragments.capital.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R

class CapitalAdapter(private val capitalList: ArrayList<String>, private val onItemClicked: (String) -> Unit): RecyclerView.Adapter<CapitalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapitalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CapitalViewHolder(layoutInflater.inflate(R.layout.item_capital,parent,false))
    }

    override fun onBindViewHolder(holder: CapitalViewHolder, position: Int) {
        val item: String = capitalList[position]
        holder.bind(item, onItemClicked)
    }

    override fun getItemCount(): Int = capitalList.size
}