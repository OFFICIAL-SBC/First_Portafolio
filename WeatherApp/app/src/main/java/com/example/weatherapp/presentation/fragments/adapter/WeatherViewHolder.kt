package com.example.weatherapp.presentation.fragments.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherapp.databinding.ItemWeatherBinding
import com.example.weatherapp.presentation.Model.WeatherDataPresentation
import com.squareup.picasso.Picasso

class WeatherViewHolder(view: View) : ViewHolder(view) {

    private val binding = ItemWeatherBinding.bind(view)

    fun render(weatherDataPresentation: WeatherDataPresentation, onItemClicked: (WeatherDataPresentation)-> Unit) {
        with(binding) {
            Picasso.get()
                .load("https://www.weatherbit.io/static/img/icons/${weatherDataPresentation.icon_code}.png")
                .into(ivWeather)
            tvCityName.text=weatherDataPresentation.city_name
            tvDescription.text=weatherDataPresentation.description
            tvTemp.text=weatherDataPresentation.temp.toString()
            tvTimezone.text=weatherDataPresentation.timezone
            tvUbication.text="Lat: ${weatherDataPresentation.lat} Lon: ${weatherDataPresentation.lon}"
            ivDeleteIcon.setOnClickListener {
                onItemClicked(weatherDataPresentation)
            }
        }
    }
}