package com.example.countriesapp.presentation.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriesapp.databinding.FragmentCountryListBinding
import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.domain.CountryItemClass
import com.example.countriesapp.framework.CountryViewModelFactory
import com.example.countriesapp.presentation.fragments.list.adapter.CountryListAdapter

class CountryListFragment : Fragment() {


    private lateinit var fragmentCountryListBinding: FragmentCountryListBinding
    private lateinit var viewModel: CountryListViewModel
    private val args: CountryListFragmentArgs by navArgs()
    private val countryList: ArrayList<CountryItemClass> = arrayListOf()
    private lateinit var countryAdapter: CountryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this, CountryViewModelFactory)[CountryListViewModel::class.java]
        fragmentCountryListBinding = FragmentCountryListBinding.inflate(inflater, container, false)
        return fragmentCountryListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryList.clear()

        countryAdapter = CountryListAdapter(countryList) { selectedCountry ->

            onItemClicked(selectedCountry)

        }
        initRecyclerView()

        val regionSelected = args.region
        viewModel.getCountryByRegion(regionSelected)

        //Observers

        viewModel.countriesDone.observe(viewLifecycleOwner, Observer { countries ->
            onCountriesDoneSuscribe(countries)
        })

        viewModel.msgDone.observe(viewLifecycleOwner, Observer { msg ->
            onMessageDoneSuscribe(msg)
        })

        viewModel.progressBarIndicatorDone.observe(viewLifecycleOwner, Observer { indicator ->
            onProgressBarIndicatorDoneSuscribe(indicator)
        })


    }

    private fun onProgressBarIndicatorDoneSuscribe(indicator: Boolean?) {
        indicator?.let {
            if (it) fragmentCountryListBinding.progressBar.visibility = VISIBLE
            else fragmentCountryListBinding.progressBar.visibility = GONE
        }

    }

    private fun onMessageDoneSuscribe(msg: String?) {
        msg?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

    }

    private fun onCountriesDoneSuscribe(countries: ArrayList<CountryItemClass>?) {
        countries?.let {
            countryAdapter.appendItems(countries)
        }

    }

    private fun initRecyclerView() {
        with(fragmentCountryListBinding.rvCountriesList) {
            layoutManager = LinearLayoutManager(this@CountryListFragment.requireContext())
            adapter = countryAdapter
            setHasFixedSize(true)
        }
    }

    fun onItemClicked(selectedCountry: CountryItemClass) {
        countryList.clear()
        countryAdapter.appendItems(countryList)
        findNavController().navigate(
            CountryListFragmentDirections.actionCountryListFragmentToCountryDetail(
                selectedCountry.cca2,0
            )
        )
    }

}