package com.example.countriesapp.presentation.fragments.tabubication.current


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentShowLocationBinding
import com.example.countriesapp.presentation.fragments.tabubication.listnearcities.ListNearCityFragment
import com.example.countriesapp.presentation.models.UbicationClass

class ShowLocationFragment : Fragment() {

    private val REQUEST_CODE = 200
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PERMISSION = 100
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

        checkCameraPermission()

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
                fabCamera.setOnClickListener {
                    capturePhoto()
                }
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

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this@ShowLocationFragment.requireContext(),
                Manifest.permission.CAMERA
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@ShowLocationFragment.requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                1
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@ShowLocationFragment.requireContext(),
                            Manifest.permission.CAMERA) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this@ShowLocationFragment.requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@ShowLocationFragment.requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    private fun capturePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null){
            binding.ivPlaceMemory.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
    }
}


