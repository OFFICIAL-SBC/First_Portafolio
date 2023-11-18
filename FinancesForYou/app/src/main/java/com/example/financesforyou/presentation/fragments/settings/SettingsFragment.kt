package com.example.financesforyou.presentation.fragments.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.financesforyou.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}