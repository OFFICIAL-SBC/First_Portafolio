package com.example.financesforyou.presentation.fragments.Reports

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavBackStackEntry
import androidx.navigation.fragment.findNavController
import com.example.financesforyou.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class ReportsFragment : Fragment() {

    private lateinit var viewModel: ReportsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        printBackStack()
    }

    private fun printBackStack(){

        val backStack: StateFlow<List<NavBackStackEntry>> = findNavController().currentBackStack
        for (entry in backStack.value){
            Log.i("HELLO5",entry.destination.displayName)
        }
    }


}