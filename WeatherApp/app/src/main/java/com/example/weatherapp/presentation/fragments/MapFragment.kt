package com.example.weatherapp.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMapBinding
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
    private var mapReady = false

    private lateinit var mapBinding: FragmentMapBinding
    private val viewModel: MapViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mapBinding = FragmentMapBinding.inflate(inflater, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fActualMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return mapBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var currentPosition = LatLng(1.85371, -76.05071)
        mMap.addMarker(MarkerOptions().position(currentPosition).title("Hello Pitalito"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition))
        mMap.setOnMapClickListener {
            currentPosition = it
            mMap.clear()
            mMap.addMarker(
                MarkerOptions().position(currentPosition)
            )
            mMap.animateCamera(CameraUpdateFactory.newLatLng(currentPosition),600,null)
        }

        mMap.setOnMarkerClickListener {
            Toast.makeText(requireContext(),"Lat: ${it.position.latitude} Lon:${it.position.longitude}",Toast.LENGTH_LONG).show()
            false
        }
    }

}