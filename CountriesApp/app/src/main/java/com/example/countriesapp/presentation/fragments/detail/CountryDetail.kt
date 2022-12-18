package com.example.countriesapp.presentation.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentCountryDetailBinding

class CountryDetail : Fragment() {


    private lateinit var viewModel: CountryDetailViewModel
    private lateinit var binding: FragmentCountryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDetailBinding.inflate(inflater,container,false)
        return inflater.inflate(R.layout.fragment_country_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}