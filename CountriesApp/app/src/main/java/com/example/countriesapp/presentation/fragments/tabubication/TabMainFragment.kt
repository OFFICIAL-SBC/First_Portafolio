package com.example.countriesapp.presentation.fragments.tabubication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentTabMainBinding
import com.example.countriesapp.presentation.fragments.tabubication.current.ShowLocationFragment
import com.example.countriesapp.presentation.fragments.tabubication.listnearcities.ListNearCityFragment
import com.example.countriesapp.presentation.models.UbicationClass
import com.google.android.material.tabs.TabLayoutMediator


class TabMainFragment : Fragment() {


    private lateinit var viewModel: TabMainViewModel
    private lateinit var binding: FragmentTabMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val args:   TabMainFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val positionObject: UbicationClass = args.currentpositionobject

        var exactPosition = "${positionObject.latitud}"

        if(positionObject.longitud > 0) exactPosition+= "+"
        exactPosition+="${positionObject.longitud}"

        viewPagerAdapter =
            ViewPagerAdapter((activity as AppCompatActivity).supportFragmentManager, lifecycle)

        viewPagerAdapter.addFragment(ShowLocationFragment.getInstance(positionObject))
        viewPagerAdapter.addFragment(ListNearCityFragment.getInstance(exactPosition))
        binding.vpShowCurrentLocation.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tlCurrentUbication, binding.vpShowCurrentLocation) { tab, index ->
            tab.text = when (index) {
                0 -> "Location"
                else -> "List"
            }
        }.attach()


    }


}