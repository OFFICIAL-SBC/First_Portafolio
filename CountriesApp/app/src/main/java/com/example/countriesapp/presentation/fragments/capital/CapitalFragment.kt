package com.example.countriesapp.presentation.fragments.capital

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentCapitalBinding
import com.example.countriesapp.framework.CountryViewModelFactory
import com.example.countriesapp.framework.local.CapitalProvider
import com.example.countriesapp.presentation.fragments.capital.adapter.CapitalAdapter

class CapitalFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var viewModel: CapitalViewModel
    private lateinit var binding: FragmentCapitalBinding
    private val capitalList: ArrayList<String> = arrayListOf()
    private lateinit var adapterCapital: CapitalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this, CountryViewModelFactory)[CapitalViewModel::class.java]
        binding = FragmentCapitalBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        capitalList.clear()
        capitalList.addAll(CapitalProvider.getCapitals())
        adapterCapital = CapitalAdapter(capitalList)
        initRecyclerView()



    }

    private fun initRecyclerView() {
        with(binding.rvCapitalList){
            adapter = adapterCapital
            layoutManager = LinearLayoutManager(this@CapitalFragment.requireContext())
            setHasFixedSize(true)
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}