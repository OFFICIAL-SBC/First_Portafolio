package com.example.financesforyou.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.financesforyou.R
import com.example.financesforyou.data.UserViewModel
import com.example.financesforyou.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private lateinit var appBarConfiguration: AppBarConfiguration
private lateinit var viewModel: UserViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //User Sesion
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.firstMoment()
        viewModel.initializeAuth()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val topLevelDestinations = setOf(R.id.transactionFragment, R.id.reportsFragment)

        appBarConfiguration = AppBarConfiguration(
            topLevelDestinations
        )

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcvHost) as NavHostFragment

        val navController = navHostFragment.navController

        setUpBottomNavMenu(navController)
        actionBarWithNavControllerAndAppBarConfig(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->

            if (nd.id == R.id.loginFragment || nd.id == R.id.registerFragment) {
                supportActionBar?.hide()
                binding.bnvMainNavigation.visibility = GONE
            } else {
                supportActionBar?.show()
                binding.bnvMainNavigation.visibility = VISIBLE
            }

        }

    }

    private fun setUpBottomNavMenu(navController: NavController) {
        binding.bnvMainNavigation.setupWithNavController(navController)
    }

    private fun actionBarWithNavControllerAndAppBarConfig(
        navController: NavController,
        appBarConfiguration: AppBarConfiguration
    ) {
        /*
        * This method will connects the navigation controller with the DEFAULT ACTION BAR and allows the navigation
        *  controller to control the action bar's appearance and behavior.
        * Basically, add navigation support to the default action bar.
        * */
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fcvHost)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}