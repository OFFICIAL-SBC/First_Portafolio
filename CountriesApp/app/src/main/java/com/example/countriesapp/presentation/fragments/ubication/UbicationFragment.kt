package com.example.countriesapp.presentation.fragments.ubication


import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentUbicationBinding
import com.example.countriesapp.framework.CountryViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.jar.Manifest

class UbicationFragment : Fragment() {


    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var viewModel: UbicationViewModel
    private lateinit var binding: FragmentUbicationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this@UbicationFragment.requireContext())
        viewModel = ViewModelProvider(this, CountryViewModelFactory)[UbicationViewModel::class.java]
        binding = FragmentUbicationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun getCurrentLocation() {

        if (checkPermissions()) {
            if (isLocationEnabled()) {

            } else {
                //Setting open here
                Toast.makeText(
                    this@UbicationFragment.requireContext(),
                    "Turn on Location",
                    Toast.LENGTH_SHORT
                ).show()

            }
        } else {
            //Request permission here
            requestPermission()
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@UbicationFragment.requireActivity(),
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this@UbicationFragment.requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this@UbicationFragment.requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this@UbicationFragment.requireContext(),
                    "GRANTED",
                    Toast.LENGTH_SHORT
                ).show()
                getCurrentLocation()
            } else {
                Toast.makeText(
                    this@UbicationFragment.requireContext(),
                    "DENIED",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}