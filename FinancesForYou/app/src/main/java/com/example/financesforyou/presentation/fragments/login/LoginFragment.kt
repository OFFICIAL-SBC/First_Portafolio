package com.example.financesforyou.presentation.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.financesforyou.R
import com.example.financesforyou.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding){
            btLogin.setOnClickListener {
                val user = tietLogin.text.toString()
                val password = tietPassword.text.toString()
                var flag = true
                if (user.isNullOrEmpty() || password.isNullOrEmpty()) {
                    onMessageDoneSuscribe("You have to type your email and password")
                    flag=false
                }
                if(flag){
                    auth.signInWithEmailAndPassword(user,password)
                        .addOnCompleteListener(){ task ->
                            if(task.isSuccessful){
                                findNavController().navigate(R.id.transactionFragment)
                            }else{
                                onMessageDoneSuscribe("Athentication failed: ${task.exception}")
                            }
                        }
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