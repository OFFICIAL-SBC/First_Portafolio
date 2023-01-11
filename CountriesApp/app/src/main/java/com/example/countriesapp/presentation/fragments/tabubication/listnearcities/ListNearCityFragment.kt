package com.example.countriesapp.presentation.fragments.tabubication.listnearcities

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.countriesapp.R

class ListNearCityFragment : Fragment() {

    private lateinit var viewModel: ListNearCityViewModel
    private var location: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_near_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) location = arguments?.getString("location")

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