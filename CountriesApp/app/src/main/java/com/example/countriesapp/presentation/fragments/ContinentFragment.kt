package com.example.countriesapp.presentation.fragments

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentContinentBinding
import com.example.countriesapp.framework.CountryViewModelFactory

class ContinentFragment : Fragment() {

    private lateinit var continentViewModel: ContinentViewModel
    private lateinit var fragmentContinentBinding: FragmentContinentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        continentViewModel =
            ViewModelProvider(this, CountryViewModelFactory)[ContinentViewModel::class.java]
        fragmentContinentBinding = FragmentContinentBinding.inflate(inflater, container, false)
        return fragmentContinentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}