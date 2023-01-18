package com.example.countriesapp.presentation.fragments.tabubication.listnearcities

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentListNearCityBinding
import com.example.countriesapp.domain.CityClass
import com.example.countriesapp.framework.CountryViewModelFactory
import com.example.countriesapp.presentation.fragments.tabubication.listnearcities.adapter.ListNearCityAdapter

class ListNearCityFragment : Fragment() {

    private lateinit var viewModel: ListNearCityViewModel
    private lateinit var binding: FragmentListNearCityBinding
    private var location: String? = null
    lateinit var adapterNear: ListNearCityAdapter
    private val nearCities = ArrayList<CityClass>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, CountryViewModelFactory)[ListNearCityViewModel::class.java]
        binding = FragmentListNearCityBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterNear = ListNearCityAdapter(nearCities)

        if(arguments != null) location = arguments?.getString("location")

        initRecyclerView()

        location?.let { viewModel.getCitiesNearToYou(it) }


        //Observers

        viewModel.msgDone.observe(viewLifecycleOwner, Observer {
            onMsgDoneSuscribe(it)
        })

        viewModel.listCityDone.observe(viewLifecycleOwner, Observer { cities ->

            onCitiesDoneSuscribe(cities)

        })

    }

    private fun onCitiesDoneSuscribe(cities: ArrayList<CityClass>?) {
        cities?.let {
            adapterNear.addCities(it)
        }
    }

    private fun initRecyclerView() {
        with(binding.rvListCitiesNear){
            layoutManager = GridLayoutManager(this@ListNearCityFragment.requireContext(),2)
            adapter = adapterNear
            setHasFixedSize(true)
        }
    }

    private fun onMsgDoneSuscribe(it: String?) {
        Toast.makeText(this@ListNearCityFragment.requireContext(),it,Toast.LENGTH_LONG).show()
    }

    companion object{
        fun getInstance(location:String): Fragment{
            val fragment = ListNearCityFragment()
            val bundle = Bundle()
            bundle.putString("location",location)
            fragment.arguments = bundle
            return fragment
        }
    }

}