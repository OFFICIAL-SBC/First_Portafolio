package com.example.weatherapp.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentFavoriteBinding
import com.example.weatherapp.framework.WeatherViewModelFactory
import com.example.weatherapp.presentation.Model.WeatherDataPresentation
import com.example.weatherapp.presentation.fragments.adapter.WeatherAdapter

class FavoriteFragment : Fragment() {


    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var favoriteBinding: FragmentFavoriteBinding
    private lateinit var weatherAdapter: WeatherAdapter
    private var weatherList: List<WeatherDataPresentation> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteViewModel=ViewModelProvider(this, WeatherViewModelFactory)[FavoriteViewModel::class.java]
        favoriteBinding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return favoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherAdapter = WeatherAdapter(weatherList)
        initRecyclerView()

        favoriteViewModel.getSavedWeatherObjects()

        //Observers
        favoriteViewModel.listLoadedDone.observe(viewLifecycleOwner, Observer {
            onListLoadedDoneSuscribe(it)
        })

    }

    private fun onListLoadedDoneSuscribe(it: List<WeatherDataPresentation>?) {
        if(!it.isNullOrEmpty()){
            weatherAdapter.appendItems(it)
        }
    }

    private fun initRecyclerView(){
        with(favoriteBinding.rvWeatherList){
            layoutManager=LinearLayoutManager(this@FavoriteFragment.requireContext())
            adapter=weatherAdapter
            setHasFixedSize(false)
        }
    }

}