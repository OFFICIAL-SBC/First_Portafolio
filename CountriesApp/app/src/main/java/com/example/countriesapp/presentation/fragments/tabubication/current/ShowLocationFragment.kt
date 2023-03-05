package com.example.countriesapp.presentation.fragments.tabubication.current


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.exifinterface.media.ExifInterface
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.countriesapp.databinding.FragmentShowLocationBinding
import com.example.countriesapp.framework.CountryViewModelFactory
import com.example.countriesapp.presentation.models.UbicationClass
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class ShowLocationFragment : Fragment() {

    private val REQUEST_CODE = 200
    private var path: String? = null
    private lateinit var viewModel: ShowLocationViewModel
    private lateinit var binding: FragmentShowLocationBinding
    var ubication: UbicationClass? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this, CountryViewModelFactory)[ShowLocationViewModel::class.java]
        binding = FragmentShowLocationBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkCameraPermission()

        if (arguments != null) {

            val currentDateTime = Calendar.getInstance().time
            val dateTimeString =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(currentDateTime)

            ubication = arguments?.getSerializable("current") as UbicationClass

            val lat = String.format("%.3f", ubication?.latitud)
            val lon = String.format("%.3f", ubication?.longitud)

            with(binding) {
                tvAddress.text = ubication?.address
                tvLatitude.text = "Latitude: $lat"
                tvLongitude.text = "longitude: $lon"
                tvDate.text = dateTimeString
                fabCamera.setOnClickListener {
                    capturePhoto()
                }

                fabFavorites.setOnClickListener {
                    viewModel.addCurrentLocation(
                        dateTimeString,
                        ubication?.address ?: "No address available",
                        path ?: ""
                    )
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

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    if ((ContextCompat.checkSelfPermission(
                            this@ShowLocationFragment.requireContext(),
                            Manifest.permission.CAMERA
                        ) ==
                                PackageManager.PERMISSION_GRANTED)
                    ) {
                        Toast.makeText(
                            this@ShowLocationFragment.requireContext(),
                            "Permission Granted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@ShowLocationFragment.requireContext(),
                        "Permission Denied",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
            2 -> {

                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {

                    if ((ContextCompat.checkSelfPermission(
                            this@ShowLocationFragment.requireContext(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) ==
                                PackageManager.PERMISSION_GRANTED)
                    ) {
                        Toast.makeText(
                            this@ShowLocationFragment.requireContext(),
                            "Permission Write Storage Granted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@ShowLocationFragment.requireContext(),
                        "Permission Write Storage Denied",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
            3 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {

                    if ((ContextCompat.checkSelfPermission(
                            this@ShowLocationFragment.requireContext(),
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ) ==
                                PackageManager.PERMISSION_GRANTED)
                    ) {
                        Toast.makeText(
                            this@ShowLocationFragment.requireContext(),
                            "Permission Read Storage Granted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@ShowLocationFragment.requireContext(),
                        "Permission Read Storage Denied",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    private fun capturePhoto() {
        if (ContextCompat.checkSelfPermission(
                this@ShowLocationFragment.requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this@ShowLocationFragment.requireActivity(),
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                2
            )

        } else if (ContextCompat.checkSelfPermission(
                this@ShowLocationFragment.requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this@ShowLocationFragment.requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                3
            )

        } else {

            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val rotatedBitmap = rotateImage(imageBitmap, 90F)
            lifecycleScope.launch {
                //that this function should be called from a background thread, as it performs file I/O operations, which can block the main UI thread and lead to performance issues.
                val file = saveBitmapToFile(rotatedBitmap)
                path = file.absolutePath
                val bitmap = BitmapFactory.decodeFile(path)
                binding.ivPlaceMemory.setImageBitmap(bitmap)
            }
        }
    }

    fun handleImageOrientation(bitmap: Bitmap): Bitmap {
        // Save the bitmap to a file
        val file = saveBitmapToFile(bitmap)

        // Read the Exif metadata from the file
        val exif = ExifInterface(file.absolutePath)

        //This method always returns 0. I dont know why.
        val orientation =
            exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)

        Log.i("ORIENTATION", "$orientation ${file.absolutePath}")

        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90F)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180F)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270F)
            else -> bitmap
        }
    }

    private fun saveBitmapToFile(bitmap: Bitmap): File {
        // Create a file to save the image
        val fileName = generateFileName()
        val file = File(context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
        file.createNewFile()

        // Compress the bitmap to the specified format and quality, and save it to the file
        val stream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        stream.flush()
        stream.close()

        return file
    }

    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    private fun generateFileName(): String {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        return "IMG_$timestamp.jpg"
    }

}


