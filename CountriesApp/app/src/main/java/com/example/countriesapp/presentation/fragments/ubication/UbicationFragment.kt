package com.example.countriesapp.presentation.fragments.ubication


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import android.location.Location
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.countriesapp.databinding.FragmentUbicationBinding
import com.example.countriesapp.framework.CountryViewModelFactory
import com.example.countriesapp.presentation.models.UbicationClass
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*


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


        binding.fabGetCurrebtPosition.setOnClickListener {
            getCurrentLocation()
        }


    }

    private fun getCurrentLocation() {

        if (checkPermissions()) {
            if (isLocationEnabled()) {

                //Final latittud and logitud
                if (ActivityCompat.checkSelfPermission(
                        this@UbicationFragment.requireActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this@UbicationFragment.requireActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this@UbicationFragment.requireActivity()) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        Toast.makeText(
                            this@UbicationFragment.requireContext(),
                            "Null Received",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val geocoder =
                            Geocoder(this@UbicationFragment.requireContext(), Locale.getDefault())
                        val list: List<Address> =
                            geocoder.getFromLocation(
                                location.latitude,
                                location.longitude,
                                1
                            ) as List<Address>
                        val ubication = UbicationClass(
                            list[0].latitude,
                            list[0].longitude,
                            list[0].getAddressLine(0),
                            list[0].countryName,
                            list[0].locality
                        )
                        findNavController().navigate(
                            UbicationFragmentDirections.actionUbicationFragmentToTabMainFragment(
                                ubication
                            )
                        )
                    }
                }

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
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
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