package com.example.countriesapp.presentation.fragments.tabubication.current

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentShowLocationBinding
import com.example.countriesapp.presentation.fragments.tabubication.listnearcities.ListNearCityFragment
import com.example.countriesapp.presentation.models.UbicationClass

class ShowLocationFragment : Fragment() {


    private lateinit var viewModel: ShowLocationViewModel
    private lateinit var binding: FragmentShowLocationBinding
    var ubication: UbicationClass? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowLocationBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {

            ubication = arguments?.getSerializable("current") as UbicationClass

            val lat = String.format("%.3f", ubication?.latitud)
            val lon = String.format("%.3f", ubication?.longitud)

            with(binding) {
                tvAddress.text = ubication?.address
                tvCountryName.text = ubication?.countryName
                tvLatitude.text = "Latitude: $lat"
                tvLongitude.text = "longitude: $lon"
                tvLocality.text = ubication?.locality
            }


        } else {
            onMessageDoneSuscribe("Ubication has not been passed to this fragment")
        }


    }

    private fun onMessageDoneSuscribe(msg: String) {
        Toast.makeText(this@ShowLocationFragment.requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun getInstance(ubication: UbicationClass): ShowLocationFragment {
            val fragment = ShowLocationFragment()
            val bundle = Bundle()
            bundle.putSerializable("current", ubication)
            fragment.arguments = bundle
            return fragment
        }
    }


}