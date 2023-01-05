package com.example.countriesapp.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.countriesapp.R
import com.example.countriesapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    //private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val toolBar = mainBinding.activityMainContent.mainToolbar

        //Setting up the tool bar as the app bar for the activity
        setSupportActionBar(toolBar)

        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        val navController = host.navController

        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id != R.id.continentFragment && nd.id != R.id.capitalFragment) {
                mainBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            } else {
                mainBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }

        //appBarConfiguration = AppBarConfiguration(navController.graph)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.continentFragment, R.id.capitalFragment, R.id.ubicationFragment),
            mainBinding.drawerLayout
        )

        //First
        setUpActionBar(navController, appBarConfiguration)

        //Second
        /*
        //When you dont use navigation component.
        //This code attach the drawer layout with the action bar (the tool bar setted as an the app bar)
        toggle = ActionBarDrawerToggle(this,mainBinding.drawerLayout,toolBar,R.string.open_drawer, R.string.close_drawer)
        mainBinding.drawerLayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
        */


        //Second
        setUpNavigationView(navController)


    }

    private fun setUpActionBar(navController: NavController, appBarConfig: AppBarConfiguration) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun setUpNavigationView(controller: NavController) {
        mainBinding.navView.setupWithNavController(controller)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //Is not neccesary this method.
        mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (mainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}