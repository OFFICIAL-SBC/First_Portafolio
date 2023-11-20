package com.example.financesforyou.presentation.fragments.settings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.financesforyou.R
import com.example.financesforyou.presentation.UserViewModel
import com.example.financesforyou.utils.Resource

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
}