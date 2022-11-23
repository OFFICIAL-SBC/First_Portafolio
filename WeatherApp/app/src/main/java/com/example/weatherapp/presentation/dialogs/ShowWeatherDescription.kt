package com.example.weatherapp.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.WeatherDialogBinding
import com.example.weatherapp.presentation.Model.WeahterDataPresentation

class ShowWeatherDescription:DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return WeatherDialogBinding.inflate(inflater,container,false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = WeatherDialogBinding.bind(view)
        val args : ShowWeatherDescriptionArgs by navArgs()
        val selectedLocation: WeahterDataPresentation = args.weatherAtributes

        binding.tvDialogTitle.text = selectedLocation.city_name
        binding.tvValueTimezone.text=selectedLocation.timezone
        binding.tvValueTemperature.text=selectedLocation.temp.toString()
        binding.tvValueAirquality.text=selectedLocation.aqi.toString()
        binding.tvValueDescription.text=selectedLocation.description
        binding.tvValueHumidity.text=selectedLocation.rh.toString()
        binding.tvValueWindSpeed.text=selectedLocation.wind_spd.toString()

        binding.bnSaveCurrentWeather.setOnClickListener { dismiss() }
        binding.bnCloseDialog.setOnClickListener { dismiss() }

    }


}