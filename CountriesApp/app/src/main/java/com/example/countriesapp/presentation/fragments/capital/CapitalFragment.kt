package com.example.countriesapp.presentation.fragments.capital

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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
    private var capitalSelected: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this, CountryViewModelFactory)[CapitalViewModel::class.java]
        binding = FragmentCapitalBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.svSerachCapital.setOnQueryTextListener(this)

        capitalList.clear()
        capitalList.addAll(CapitalProvider.getCapitals())
        adapterCapital = CapitalAdapter(capitalList) { capital ->

            onCapitalSelected(capital)

        }
        initRecyclerView()


        //Listener
        binding.btSearchButton.setOnClickListener {
            if (capitalSelected == null) Toast.makeText(
                requireContext(),
                "Select a capital",
                Toast.LENGTH_LONG
            ).show()
            else findNavController().navigate(
                CapitalFragmentDirections.actionCapitalFragmentToCountryDetail(
                    capitalSelected!!,
                    1
                )
            )
        }


    }

    private fun initRecyclerView() {
        with(binding.rvCapitalList) {
            adapter = adapterCapital
            layoutManager = LinearLayoutManager(this@CapitalFragment.requireContext())
            setHasFixedSize(true)
        }

    }

    private fun filterCapitals(query: String) {

        val auxiliarList = CapitalProvider.getCapitals().filter { it.contains(query) }
        capitalList.clear()

        if (query.isNullOrEmpty()) capitalList.addAll(CapitalProvider.getCapitals())
        else capitalList.addAll(auxiliarList)
        adapterCapital.notifyDataSetChanged()
    }

    private fun onCapitalSelected(capital: String) {
        binding.btSearchButton.text = capital
        capitalSelected = capital
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            filterCapitals(it)
        }
        return true
    }


}