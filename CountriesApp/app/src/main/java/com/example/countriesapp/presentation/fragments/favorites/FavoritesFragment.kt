package com.example.countriesapp.presentation.fragments.favorites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentFavoritesBinding
import com.example.countriesapp.framework.CountryViewModelFactory
import com.example.countriesapp.framework.local.room.CountryEntity
import com.example.countriesapp.presentation.fragments.favorites.adapter.FavoriteAdapter

class FavoritesFragment : Fragment() {


    private lateinit var viewModel: FavoritesViewModel
    private lateinit var binding: FragmentFavoritesBinding
    private val entityList: ArrayList<CountryEntity> = arrayListOf()
    private lateinit var adapterFavorite:FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this,CountryViewModelFactory)[FavoritesViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        entityList.clear()
        adapterFavorite = FavoriteAdapter(entityList)
        initRecyclerView()

        viewModel.getAllSavedPlaces()

        //Observers
        viewModel.savedPlacesDone.observe(viewLifecycleOwner, Observer {
            onArrayListDoneSuscribe(it)
        })
    }

    private fun onArrayListDoneSuscribe(places: java.util.ArrayList<CountryEntity>?) {
        if (places != null){
            entityList.clear()
            entityList.addAll(places)
            adapterFavorite.notifyDataSetChanged()
        }else{
            onMessageDoneSuscribe("Something went wrong with Room")
        }
    }

    private fun onMessageDoneSuscribe(msg: String) {
        Toast.makeText(requireContext(),msg,Toast.LENGTH_LONG).show()
    }

    private fun initRecyclerView(){
        with(binding.rvShowSavedPlaces){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterFavorite
            setHasFixedSize(true)
        }
    }

}