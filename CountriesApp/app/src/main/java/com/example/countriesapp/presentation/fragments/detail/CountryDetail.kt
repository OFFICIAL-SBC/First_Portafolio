package com.example.countriesapp.presentation.fragments.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentCountryDetailBinding
import com.example.countriesapp.domain.CityClass
import com.example.countriesapp.domain.CountryClass
import com.example.countriesapp.domain.CountryItemClass
import com.example.countriesapp.framework.CountryViewModelFactory
import com.example.countriesapp.presentation.fragments.detail.adapter.CountryDetailAdapter
import com.squareup.picasso.Picasso

class CountryDetail : Fragment() {


    private lateinit var viewModel: CountryDetailViewModel
    private lateinit var binding: FragmentCountryDetailBinding
    private val args: CountryDetailArgs by navArgs()
    private val cityList: ArrayList<CityClass> = arrayListOf()
    private lateinit var detailAdapter: CountryDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDetailBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this, CountryViewModelFactory)[CountryDetailViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityList.clear()
        detailAdapter = CountryDetailAdapter(cityList)
        initRecyclerView()

        val countrySelected: CountryItemClass = args.countryItemClass

        viewModel.getCountryByCode(countrySelected.cca2)


        //Observers
        viewModel.countryDone.observe(viewLifecycleOwner, Observer {
            onCountryDoneSuscribe(it)
        })

        viewModel.citiesDone.observe(viewLifecycleOwner, Observer { newList ->
            onCitiesDoneSuscribe(newList)
        })

        viewModel.msgDone.observe(viewLifecycleOwner, Observer { msg ->
            onMsgDoneSuscribe(msg)
        })



    }

    private fun onMsgDoneSuscribe(msg: String?) {
        msg?.let {
            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
        }
    }

    private fun onCitiesDoneSuscribe(newList: ArrayList<CityClass>?) {
        newList?.let{
            detailAdapter.appendItems(it)
        }
    }

    private fun onCountryDoneSuscribe(selectedCountry: CountryClass?) {
        selectedCountry?.let { actual ->
            with(binding) {
                var borders = "Borders: "
                var currencies = "Currencies: "
                var languages = "Lenguages: "


                if (!actual.borders.isNullOrEmpty()) actual.borders.forEach { borders += "$it, " }
                else borders = "There is not borders availables"


                for(key in actual.languages.keys) languages+= "${actual.languages[key]}, "

                tvLenguages.text = languages

                tvCapital.text ="Capital: ${actual.capital[0]}"
                tvBorders.text = borders
                tvCca2.text = actual.cca2
                tvNameCountry.text = actual.name.common

                for (key in actual.currencies.keys) currencies += "${actual.currencies[key]?.name}, "
                tvCunrrencies.text = currencies
                tvregion.text = actual.region
                tvExactUbication.text = "lat: ${actual.latlng[0]} lon: ${actual.latlng[1]}"
                tvDemonyms.text ="Demonyms: " + actual.demonyms.eng.m
                tvPopulation.text = "Population: "+ actual.population.toString()
                tvTimezones.text = "Timezone: " + actual.timezones[0]
                tvSubregion.text = actual.subregion
                Picasso.get()
                    .load(actual.flags.png)
                    .into(imFlagImage)

                viewModel.getCitiesByCountry(actual.cca2)
            }

        }
    }

    private fun initRecyclerView() {
        with(binding.rvCities) {
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(
                this@CountryDetail.requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }
    }

}