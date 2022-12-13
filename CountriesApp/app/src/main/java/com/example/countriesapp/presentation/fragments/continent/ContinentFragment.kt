package com.example.countriesapp.presentation.fragments.continent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.countriesapp.databinding.FragmentContinentBinding
import com.example.countriesapp.framework.CountryViewModelFactory
import com.ramotion.circlemenu.CircleMenuView


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

        val menu: CircleMenuView = fragmentContinentBinding.circleMenu
        menu.eventListener = object : CircleMenuView.EventListener() {
            override fun onMenuOpenAnimationStart(view: CircleMenuView) {
                Log.d("DADO", "onMenuOpenAnimationStart")
            }

            override fun onMenuOpenAnimationEnd(view: CircleMenuView) {
                Log.d("DADO", "onMenuOpenAnimationEnd")
            }

            override fun onMenuCloseAnimationStart(view: CircleMenuView) {
                Log.d("DADO", "onMenuCloseAnimationStart")
            }

            override fun onMenuCloseAnimationEnd(view: CircleMenuView) {
                Log.d("DADO", "onMenuCloseAnimationEnd")
            }

            override fun onButtonClickAnimationStart(view: CircleMenuView, index: Int) {
                Log.d("DADO", "onButtonClickAnimationStart| index: $index")
            }

            override fun onButtonClickAnimationEnd(view: CircleMenuView, index: Int) {
                when (index) {
                    0 -> navigateToList("africa")
                    1 -> navigateToList("americas")
                    2 -> navigateToList("europe")
                    3 -> navigateToList("asia")
                    4 -> navigateToList("oceania")
                }
            }

            override fun onButtonLongClick(view: CircleMenuView, index: Int): Boolean {
                Log.d("DADO", "onButtonLongClick| index: $index")
                return true
            }

            override fun onButtonLongClickAnimationStart(view: CircleMenuView, index: Int) {
                Log.d("DADO", "onButtonLongClickAnimationStart| index: $index")
            }

            override fun onButtonLongClickAnimationEnd(view: CircleMenuView, index: Int) {
                Log.d("DADO", "onButtonLongClickAnimationEnd| index: $index")
            }
        }
    }

    private fun navigateToList(region: String) {
        findNavController().navigate(
            ContinentFragmentDirections.actionContinentFragmentToCountryListFragment(
                region
            )
        )
    }

}