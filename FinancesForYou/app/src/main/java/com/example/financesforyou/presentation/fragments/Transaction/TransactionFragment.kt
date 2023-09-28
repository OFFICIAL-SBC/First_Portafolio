package com.example.financesforyou.presentation.fragments.Transaction

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
import com.example.financesforyou.data.UserViewModel
import com.example.financesforyou.presentation.fragments.login.LoginFragment

class TransactionFragment : Fragment() {

    private lateinit var viewModel: TransactionViewModel
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[TransactionViewModel::class.java]
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Checking if there is a open user sesion
        userViewModel.userIndicatorDone.observe(viewLifecycleOwner, Observer { user ->
            if (!user) { //User == null; remember that user will be a class That will contain all the user data.
                findNavController().navigate(R.id.loginFragment)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentBackStackEntry = findNavController().currentBackStackEntry!!
        val saveStateHandle = currentBackStackEntry.savedStateHandle
        saveStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL) //This execute only if the pair (key=LoginFragment.LOGIN_SUCCESSFUL, value = true or false) exist in the saveStateHandle
            .observe(currentBackStackEntry, Observer { success ->
                if(!success){
                    findNavController().popBackStack()
                }
            })
    }

    private fun onSuscribeMsgDone(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

}