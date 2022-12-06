package com.example.countriesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.countriesapp.R
import com.example.countriesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        //Setting up the tool bar as the app bar for the activity
        setSupportActionBar(mainBinding.activityMainContent.mainToolbar)

        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        val navController = host.navController

        //appBarConfiguration = AppBarConfiguration(navController.graph)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.continentFragment),
            mainBinding.drawerLayout
        )

        //First
        setUpNavigationView(navController)

        //Second
        setUpActionBar(navController,appBarConfiguration)


    }

    private fun setUpActionBar(navController: NavController, appBarConfig : AppBarConfiguration) {
        setupActionBarWithNavController(navController,appBarConfig)
    }

    private fun setUpNavigationView(controller: NavController) {
        mainBinding.navView.setupWithNavController(controller)
    }
}