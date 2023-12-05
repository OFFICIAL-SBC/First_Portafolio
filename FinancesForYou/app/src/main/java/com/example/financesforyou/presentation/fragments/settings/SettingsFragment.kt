package com.example.financesforyou.presentation.fragments.settings

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavBackStackEntry
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.financesforyou.R
import com.example.financesforyou.presentation.UserViewModel
import com.example.financesforyou.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {


    private val userViewModel: UserViewModel by activityViewModels()
    private var signOutPreference: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signOutPreference = findPreference("signout")

        signOutPreference?.setOnPreferenceClickListener {
            signOutFromSettings()
            true
        }

        printBackStack()

    }

    private fun signOutFromSettings(){
        userViewModel.signOut().observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Error -> {
                    onMessageDoneSuscribe(it.message!!)
                }
                is Resource.Loading -> {}
                is Resource.Success -> {
                    onMessageDoneSuscribe("Bye, come back soon!!")
                }
            }
        })
    }

    private fun onMessageDoneSuscribe(msg: String){
        Toast.makeText(requireContext(),msg,Toast.LENGTH_SHORT).show()
    }

    private fun printBackStack(){

        val backStack: StateFlow<List<NavBackStackEntry>> = findNavController().currentBackStack
        for (entry in backStack.value){
            Log.i("HELLO5",entry.destination.displayName)
        }
    }
}