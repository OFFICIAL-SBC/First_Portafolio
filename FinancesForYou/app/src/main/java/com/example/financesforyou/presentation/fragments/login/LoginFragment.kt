package com.example.financesforyou.presentation.fragments.login

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
import com.example.financesforyou.databinding.FragmentLoginBinding
import com.example.financesforyou.presentation.UserViewModel
import java.util.regex.Pattern

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
        //auth = FirebaseAuth.getInstance()
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle[LOGIN_SUCCESSFUL] = false //Create this savedStatedHandle in the transaction fragment

        with(binding){
            btLogin.setOnClickListener {
                val user = tietLogin.text.toString()
                val password = tietPassword.text.toString()
                var flag = true
                if (user.isEmpty() || password.isEmpty()) {
                    onMessageDoneSuscribe("You have to type your email and password")
                    flag=false
                }else if(!isValidEmail(user)){
                    onMessageDoneSuscribe("Please, type a valid email")
                    flag = false
                }else if(password.length < 6){
                    onMessageDoneSuscribe("Password must be a at lest of 6 characters")
                    flag = false
                }
                if(flag){
                    userViewModel.openSesion(user,password).observe(viewLifecycleOwner, Observer { result ->
                        onMessageDoneSuscribe(userViewModel.returnMessage())
                        if(result){
                            savedStateHandle[LOGIN_SUCCESSFUL] = true
                            val startDestination = findNavController().graph.startDestinationId
                            val navOptions = NavOptions.Builder()
                                .setPopUpTo(startDestination, true)
                                .build()
                            findNavController().navigate(startDestination,null,navOptions)
                        }else{
                            tietLogin.text?.clear()
                            tietPassword.text?.clear()
                        }
                    })
                }
            }
            tvNoAccount.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }

    }

    private fun onMessageDoneSuscribe(msg: String) {
        Toast.makeText(requireContext(),msg,Toast.LENGTH_SHORT).show()
    }

    fun isValidEmail(str: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }

}