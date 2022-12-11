package com.example.countriesapp.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.countriesapp.R
import com.example.countriesapp.framework.CountryViewModelFactory

class ContinentFragment : Fragment() {

    private lateinit var continentViewModel: ContinentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        continentViewModel = ViewModelProvider(this,CountryViewModelFactory)[ContinentViewModel::class.java]
        return inflater.inflate(R.layout.fragment_continent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        continentViewModel.getCountryByCapital("lima")
    }

}