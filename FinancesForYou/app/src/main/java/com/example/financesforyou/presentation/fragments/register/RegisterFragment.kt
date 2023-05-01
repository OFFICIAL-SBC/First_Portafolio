package com.example.financesforyou.presentation.fragments.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.financesforyou.R
import com.example.financesforyou.databinding.FragmentRegisterBinding
import com.example.financesforyou.presentation.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import java.util.regex.Pattern

class RegisterFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var auth: FirebaseAuth
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
                var flag = true
                if (user.isEmpty() || password.isEmpty() || conPassword.isEmpty()) {
                    onMessageDoneSuscribe("You have to fill in all the fields.")
                    flag = false
                }else if(!isValidEmail(user)){
                    onMessageDoneSuscribe("Please type a valid email!")
                    flag = false
                }
                else if (password != conPassword) {
                    onMessageDoneSuscribe("The passwords dont match")
                    flag = false
                }else if(password.length < 6){
                    onMessageDoneSuscribe("The password's lenght has to be at least 6 caracters")
                    flag = false
                }
                if (flag) {
                    userViewModel.createNewUser(user,password).observe(viewLifecycleOwner, Observer { result ->
                        onMessageDoneSuscribe(userViewModel.returnMessage())
                        if(result) findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                        else{
                            tietEmail.text?.clear()
                            tietPassword.text?.clear()
                            tietConPassword.text?.clear()
                        }
                    })
                }
            }
        }
    }

    fun onMessageDoneSuscribe(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    fun isValidEmail(str: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }

}