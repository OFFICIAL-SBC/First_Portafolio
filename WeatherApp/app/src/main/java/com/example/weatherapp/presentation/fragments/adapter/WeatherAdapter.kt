package com.example.weatherapp.presentation.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.presentation.Model.WeatherDataPresentation

class WeatherAdapter(private var weatherList: List<WeatherDataPresentation>) : RecyclerView.Adapter<WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return WeatherViewHolder(layoutInflater.inflate(R.layout.item_weather,parent,false))
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = weatherList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = weatherList.size

    fun appendItems(newList: List<WeatherDataPresentation>){
        weatherList = newList
        notifyDataSetChanged()
    }

}