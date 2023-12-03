package com.example.financesforyou.presentation.fragments.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.financesforyou.databinding.FragmentLoginBinding
import com.example.financesforyou.presentation.UserViewModel
import com.example.financesforyou.utils.Resource
import java.util.regex.Pattern
import kotlinx.coroutines.flow.StateFlow

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var savedStateHandle: SavedStateHandle

    //private lateinit var auth: FirebaseAuth
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel.setLiveDataToNull()
        //auth = FirebaseAuth.getInstance()
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        visibilityView()
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle[LOGIN_SUCCESSFUL] =
            false //Create this savedStatedHandle in the transaction fragment



        with(binding) {
            btLogin.setOnClickListener {
                val user = tietLogin.text.toString()
                val password = tietPassword.text.toString()
                val error = if (user.isEmpty() || password.isEmpty()) {
                    "You have to type your email and password"
                } else if (!isValidEmail(user)) {
                    "Please, type a valid email"
                } else if (password.length < 6) {
                    "Password must be a at lest of 6 characters"
                } else {
                    null
                }
                if (error.isNullOrBlank()) {
                    userViewModel.openSesion(user, password)
                } else {
                    onMessageDoneSuscribe(error)
                }
            }
            tvNoAccount.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }


            userViewModel.userSignInStatus.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is Resource.Error -> {
                        visibilityView()
                        onMessageDoneSuscribe(it.message!!)
                        tietLogin.text?.clear()
                        tietPassword.text?.clear()
                    }
                    is Resource.Loading -> {
                        visibilityGone()
                    }
                    is Resource.Success -> {
                        savedStateHandle[LOGIN_SUCCESSFUL] = true
                    }
                    null -> {}
                }
            })
        }

        printBackStack()

    }

    private fun onMessageDoneSuscribe(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun visibilityGone(){
        with(binding){
            tvTitleLogin.visibility = GONE
            tvNoAccount.visibility=GONE
            tilEmailLogin.visibility= GONE
            tilPaswordLogin.visibility=GONE
            btLogin.visibility= GONE
            pbLoading.visibility=VISIBLE
        }
    }

    private fun visibilityView(){
        with(binding){
            tvTitleLogin.visibility = VISIBLE
            tvNoAccount.visibility=VISIBLE
            tilEmailLogin.visibility= VISIBLE
            tilPaswordLogin.visibility=VISIBLE
            btLogin.visibility= VISIBLE
            pbLoading.visibility=GONE
        }
    }

    fun isValidEmail(str: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }

    private fun printBackStack(){

        val backStack: StateFlow<List<NavBackStackEntry>> = findNavController().currentBackStack
        for (entry in backStack.value){
            Log.i("HELLO8",entry.destination.displayName)
        }
    }

}