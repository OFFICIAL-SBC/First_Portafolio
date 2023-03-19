package com.example.countriesapp.presentation.fragments.favorites.dialog

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentAddShowMemoryDescriptionDialogBinding
import com.example.countriesapp.framework.CountryViewModelFactory

class AddShowMemoryDescriptionDialog : DialogFragment() {

    private lateinit var viewModel: AddShowMemoryDescriptionDialogViewModel
    private lateinit var binding: FragmentAddShowMemoryDescriptionDialogBinding
    private val args: AddShowMemoryDescriptionDialogArgs by navArgs()
    private lateinit var text: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddShowMemoryDescriptionDialogBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            this,
            CountryViewModelFactory
        )[AddShowMemoryDescriptionDialogViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogDimension(85, 65)
        var description: String = args.memoryDescription
        val id: Int = args.placeId

        if (description.isEmpty()) description = "there is not a description"

        binding.tvActualDescription.text = description

        viewModel.indicatorDone.observe(viewLifecycleOwner, Observer { flag ->
            onIndicatorDoneSuscribe(flag)
        })

        binding.fabCloseButton.setOnClickListener {
            findNavController().navigate(AddShowMemoryDescriptionDialogDirections.actionAddShowMemoryDescriptionDialogToFavoritesFragment())
        }
        binding.fabConfirtamitionButton.setOnClickListener {
            if (!binding.tietActualContent.text?.isEmpty()!!) {
                text = binding.tietActualContent.text.toString()
                viewModel.updateDescription(id, text)
            }
        }

        binding.fabResetButton.setOnClickListener {
            binding.tietActualContent.text?.clear()
        }

    }

    private fun onIndicatorDoneSuscribe(flag: Boolean?) {
        flag?.let {
            onMessageDoneSuscribe("Place description is up-to-date")
            if (flag) binding.tvActualDescription.text = text
        }
    }

    private fun onMessageDoneSuscribe(msm: String) {
        Toast.makeText(requireContext(), msm, Toast.LENGTH_LONG).show()
    }

    private fun DialogFragment.setDialogDimension(width: Int, height: Int) {
        val percentW = width.toFloat() / 100
        val percentH = height.toFloat() / 100
        val dm: DisplayMetrics = Resources.getSystem().displayMetrics
        val rect: Rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWith: Float = rect.width() * percentW
        val percentHeight: Float = rect.height() * percentH
        dialog?.window?.setLayout(percentWith.toInt(), percentHeight.toInt())
    }


}


