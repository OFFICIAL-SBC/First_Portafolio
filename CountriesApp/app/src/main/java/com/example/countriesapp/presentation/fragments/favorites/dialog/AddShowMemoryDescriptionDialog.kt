package com.example.countriesapp.presentation.fragments.favorites.dialog

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentAddShowMemoryDescriptionDialogBinding

class AddShowMemoryDescriptionDialog : DialogFragment() {

    private lateinit var viewModel: AddShowMemoryDescriptionDialogViewModel
    private lateinit var binding: FragmentAddShowMemoryDescriptionDialogBinding
    private val args: AddShowMemoryDescriptionDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddShowMemoryDescriptionDialogBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogDimension(85,65)
        var description: String = args.memoryDescription

        if (description.isEmpty()) description = "there is not a description"

        binding.tvActualDescription.text = description


        binding.fabCloseButton.setOnClickListener {
            dismiss()
        }
        binding.fabConfirtamitionButton.setOnClickListener {

        }

        binding.fabResetButton.setOnClickListener {
            binding.tietActualContent.text?.clear()
        }

    }

    private fun DialogFragment.setDialogDimension(width: Int, height: Int){
        val percentW = width.toFloat()/100
        val percentH = height.toFloat()/100
        val dm:DisplayMetrics = Resources.getSystem().displayMetrics
        val rect: Rect =dm.run { Rect(0,0,widthPixels,heightPixels) }
        val percentWith: Float =rect.width()*percentW
        val percentHeight: Float = rect.height()*percentH
        dialog?.window?.setLayout(percentWith.toInt(),percentHeight.toInt())
    }


}


