package com.example.examplemvvm3.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.examplemvvm3.databinding.ActivityMainBinding
import com.example.examplemvvm3.ui.adapter.ViewPagerAdapter
import com.example.examplemvvm3.ui.viewmodel.QuoteViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private val quoteViewModel: QuoteViewModel by viewModels() // Esto se puede gracias a la librerias de fragments
                                                               // y activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,lifecycle)

        viewPagerAdapter.addFragment(QuoteFragment())
        viewPagerAdapter.addFragment(AddQuoteFragment())
        binding.vpFragments.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tlPrinciple,binding.vpFragments){ tab, index ->
            tab.text=when(index){
                0 -> "QUOTES"
                else -> "ADD"
            }
        }.attach()

    }


}