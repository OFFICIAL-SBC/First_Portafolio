package com.example.weatherapp.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMapBinding
import com.example.weatherapp.framework.WeatherViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    companion object {
        fun newInstance() = MapFragment()
    }

    private lateinit var mMap: GoogleMap
    private var currentPosition: LatLng? = null

    private lateinit var mapBinding: FragmentMapBinding
    private lateinit var viewModel: MapViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, WeatherViewModelFactory)[MapViewModel::class.java]
        mapBinding = FragmentMapBinding.inflate(inflater, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fActualMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return mapBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Observers
        viewModel.msgDone.observe(viewLifecycleOwner, Observer { msg ->
            onMessageDoneSuscribe(msg)
        })

        mapBinding.fabGetWeather.setOnClickListener {
            currentPosition?.let { it -> viewModel.getCurrentWeather(it) }
        }
    }

    private fun onMessageDoneSuscribe(msg: String?) {
        if (!msg.isNullOrEmpty()){
            Toast.makeText(
                requireContext(),
                msg,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (currentPosition == null) currentPosition = LatLng(1.85371, -76.05071)
        mMap.addMarker(
            MarkerOptions().position(currentPosition ?: LatLng(1.85371, -76.05071))
                .title("Hello Pitalito")
        )
        mMap.moveCamera(
            CameraUpdateFactory.newLatLng(
                currentPosition ?: LatLng(
                    1.85371,
                    -76.05071
                )
            )
        )
        mMap.setOnMapClickListener {
            currentPosition = it
            mMap.clear()
            mMap.addMarker(
                MarkerOptions().position(currentPosition!!)
            )
            mMap.animateCamera(CameraUpdateFactory.newLatLng(currentPosition!!), 600, null)
        }

        mMap.setOnMarkerClickListener {
            onMessageDoneSuscribe("Lat: ${it.position.latitude} Lon:${it.position.longitude}")
            false
        }
    }

}