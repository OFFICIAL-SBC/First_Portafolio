package com.example.financesforyou.presentation.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.financesforyou.R
import com.example.financesforyou.databinding.FragmentLoginBinding
import com.example.financesforyou.presentation.UserViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var savedStateHandle: SavedStateHandle
    //private lateinit var auth: FirebaseAuth

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //auth = FirebaseAuth.getInstance()
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle[LoginFragment.LOGIN_SUCCESSFUL] = false

        with(binding){
            btLogin.setOnClickListener {
                val user = tietLogin.text.toString()
                val password = tietPassword.text.toString()
                var flag = true
                if (user.isEmpty() || password.isEmpty()) {
                    onMessageDoneSuscribe("You have to type your email and password")
                    flag=false
                }
                if(flag){
                    userViewModel.openSesion(user,password).observe(viewLifecycleOwner, Observer { result ->
                        if(result){
                            savedStateHandle[LOGIN_SUCCESSFUL] = true
                            val startDestination = findNavController().graph.startDestinationId
                            val navOptions = NavOptions.Builder()
                                .setPopUpTo(startDestination, true)
                                .build()
                            findNavController().navigate(startDestination,null,navOptions)
                        }else{
                            onMessageDoneSuscribe("Athentication failed: Unable to open sesion")
                        }
                    })
                    /*
                    auth.signInWithEmailAndPassword(user,password)
                        .addOnCompleteListener(){ task ->
                            if(task.isSuccessful){
                                val startDestination = findNavController().graph.startDestinationId
                                val navOptions = NavOptions.Builder()
                                    .setPopUpTo(startDestination, true)
                                    .build()

                                findNavController().navigate(startDestination,null,navOptions)
                            }else{
                                onMessageDoneSuscribe("Athentication failed: ${task.exception}")
                            }
                        }*/
                }
            }
            tvNoAccount.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }

    }

    private fun onMessageDoneSuscribe(msg: String) {
        Toast.makeText(requireContext(),msg,Toast.LENGTH_LONG).show()
    }

}