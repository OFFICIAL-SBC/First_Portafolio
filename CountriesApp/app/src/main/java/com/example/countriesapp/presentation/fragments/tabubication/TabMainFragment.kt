package com.example.countriesapp.presentation.fragments.tabubication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentTabMainBinding
import com.example.countriesapp.presentation.fragments.tabubication.current.ShowLocationFragment
import com.example.countriesapp.presentation.fragments.tabubication.listnearcities.ListNearCityFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class TabMainFragment : Fragment() {


    private lateinit var viewModel: TabMainViewModel
    private lateinit var binding: FragmentTabMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupTabLayout()


    }

    private fun setupTabLayout() {
        val adapter = FragmentPagerItemAdapter(
            childFragmentManager,
            FragmentPagerItems.with(activity)
                .add("Location", ShowLocationFragment::class.java)
                .add("List",ListNearCityFragment::class.java)
                .create()
        )

        binding.viewpager.adapter = adapter
        binding.viewpagertab.setViewPager(binding.viewpager)
    }

}