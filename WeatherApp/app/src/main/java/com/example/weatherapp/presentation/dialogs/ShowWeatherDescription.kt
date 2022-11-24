package com.example.weatherapp.presentation.dialogs

import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.WeatherDialogBinding
import com.example.weatherapp.presentation.Model.WeahterDataPresentation
import com.squareup.picasso.Picasso

class ShowWeatherDescription : DialogFragment() {

    private lateinit var binding: WeatherDialogBinding
    private val args: ShowWeatherDescriptionArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeatherDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setWidthPercent(80)


        val selectedLocation: WeahterDataPresentation = args.weatherAtributes

        binding.tvDialogTitle.text = selectedLocation.city_name
        binding.tvValueTimezone.text = selectedLocation.timezone
        binding.tvValueTemperature.text = selectedLocation.temp.toString()
        binding.tvValueAirquality.text = selectedLocation.aqi.toString()
        binding.tvValueDescription.text = selectedLocation.description
        binding.tvValueHumidity.text = selectedLocation.rh.toString()
        binding.tvValueWindSpeed.text = selectedLocation.wind_spd.toString()
        Picasso.get()
            .load("https://www.weatherbit.io/static/img/icons/${selectedLocation.icon_code}.png")
            .into(binding.ivDialogIcon)

        binding.bnSaveCurrentWeather.setOnClickListener { dismiss() }
        binding.bnCloseDialog.setOnClickListener {
            findNavController().navigate(ShowWeatherDescriptionDirections.actionShowWeatherDescriptionToMapFragment())
        }

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    private fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm: DisplayMetrics = Resources.getSystem().displayMetrics
        val rect: Rect = dm.run { Rect(0, 0, this.widthPixels, this.heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }


}