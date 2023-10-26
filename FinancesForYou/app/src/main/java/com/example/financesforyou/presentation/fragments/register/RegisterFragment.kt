package com.example.financesforyou.presentation.fragments.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.financesforyou.databinding.FragmentRegisterBinding
import com.example.financesforyou.presentation.UserViewModel
import com.example.financesforyou.presentation.fragments.login.LoginFragment
import com.example.financesforyou.utils.Resource
import com.google.firebase.auth.AuthResult
import java.util.regex.Pattern

class RegisterFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentRegisterBinding
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel.setLiveDataToNull()
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            btSaveUser.setOnClickListener {
                val user = tietEmail.text.toString()
                val password = tietPassword.text.toString()
                val conPassword = tietConPassword.text.toString()

                val error = if (user.isEmpty() || password.isEmpty() || conPassword.isEmpty()) {
                    "You have to fill in all the fields."
                }else if(!isValidEmail(user)){
                    "Please type a valid email!"
                }
                else if (password.length < 6) {
                    "The password's lenght has to be at least 6 caracters"
                }else if(password != conPassword){
                    "The passwords dont match"
                }else{
                    null
                }

                if (error.isNullOrBlank()) {
                    userViewModel.createNewUser(user,password)
                }else{
                    onMessageDoneSuscribe(error)
                }
            }

            userViewModel.userRegistrationStatus?.observe(viewLifecycleOwner, Observer {
                when(it){
                    is Resource.Error ->{
                        onMessageDoneSuscribe(it.message!!)
                        tietEmail.text?.clear()
                        tietPassword.text?.clear()
                        tietConPassword.text?.clear()
                    }
                    is Resource.Loading -> {
                        onMessageDoneSuscribe("Loading")
                    }
                    is Resource.Success -> {
                        onMessageDoneSuscribe("The user has been registered correctly")
                        //findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                        findNavController().popBackStack()
                    }

                    null ->{}
                }
            })


        }
    }


    fun onMessageDoneSuscribe(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    fun isValidEmail(str: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }

}