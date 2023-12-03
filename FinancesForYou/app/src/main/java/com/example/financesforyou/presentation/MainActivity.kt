package com.example.financesforyou.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.financesforyou.R
import com.example.financesforyou.databinding.ActivityMainBinding
import com.example.financesforyou.framework.FinanceViewModelFactory
import com.example.financesforyou.presentation.fragments.login.LoginFragment
import com.example.financesforyou.utils.Resource

private lateinit var binding: ActivityMainBinding
private lateinit var appBarConfiguration: AppBarConfiguration
private lateinit var userViewModel: UserViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //User Sesion
        userViewModel = ViewModelProvider(this,FinanceViewModelFactory)[UserViewModel::class.java]
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

            if (nd.id == R.id.loginFragment || nd.id == R.id.registerFragment || nd.id == R.id.loadingFragment ) {
                supportActionBar?.hide()
                binding.bnvMainNavigation.visibility = GONE
            } else {
                if (nd.id != R.id.settingsFragment) binding.bnvMainNavigation.visibility = VISIBLE
                else binding.bnvMainNavigation.visibility = GONE
                supportActionBar?.show()
            }

        }

        getAuthState()

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
        * controller to control the action bar's appearance and behavior.
        * Basically, add navigation support to the default action bar.
        * */
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fcvHost)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun getAuthState(){
        userViewModel.getAuthState().observe(this, Observer{userSidnedIn ->
            Log.i("HELLO1","xxx")
            if(userSidnedIn == null){
                userViewModel.setNullUser()
                navigatePopingUpTo()
            }else{
                //Actually, we need to use previous becase ill add a loading fragment
                if(findNavController(R.id.fcvHost).currentBackStackEntry?.destination?.id == R.id.loginFragment){
                    userViewModel.getUserFromCloudFireastore(userSidnedIn.id!!).observe(this, Observer {
                            when(it){
                                is Resource.Error -> {
                                    findNavController(R.id.fcvHost).popBackStack(R.id.loginFragment,false)
                                    onMessageDoneSuscribe(it.message!!)
                                }
                                is Resource.Loading -> {findNavController(R.id.fcvHost).navigate(R.id.loadingFragment)}
                                is Resource.Success -> {
                                    userViewModel.setUserData(it.data!!)
                                    navigatePopingUpTo()
                                }
                            }
                        })
                }else if(findNavController(R.id.fcvHost).currentBackStackEntry?.destination?.id == R.id.registerFragment){
                 userViewModel.setUserData(userSidnedIn)
                 userViewModel.createNewUserInCloudFireStore().observe(this, Observer {
                     when (it) {
                         is Resource.Error -> {
                             findNavController(R.id.fcvHost).popBackStack(R.id.registerFragment,false)
                             onMessageDoneSuscribe(it.message!!)
                         }
                         is Resource.Loading -> {
                             findNavController(R.id.fcvHost).navigate(R.id.loadingFragment)
                         }
                         is Resource.Success -> {
                             navigatePopingUpTo()
                         }
                     }
                 })
                }

            }
        })
    }

    fun onMessageDoneSuscribe(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.overload_main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.go_settings_option -> findNavController(R.id.fcvHost).navigate(R.id.settingsFragment)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun navigatePopingUpTo() {
       // val navController = findNavController(R.id.fcvHost) //You have to pass the fragment container view (the NavHostFragment)
        //navController.popBackStack(navController.graph.startDestinationId,false)
        val startDestination = findNavController(R.id.fcvHost).graph.startDestinationId
        val savedStateHandle =
            findNavController(R.id.fcvHost).getBackStackEntry(startDestination).savedStateHandle
        savedStateHandle[LoginFragment.LOGIN_SUCCESSFUL] = true
        val navOptions = NavOptions.Builder()
            .setPopUpTo(startDestination, true)
            .build()
        findNavController(R.id.fcvHost).navigate(startDestination, null, navOptions)
    }

}