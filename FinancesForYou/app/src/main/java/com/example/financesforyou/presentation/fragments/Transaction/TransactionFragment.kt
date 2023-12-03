package com.example.financesforyou.presentation.fragments.Transaction

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavBackStackEntry
import androidx.navigation.fragment.findNavController
import com.example.financesforyou.R
import com.example.financesforyou.presentation.UserViewModel
import com.example.financesforyou.presentation.fragments.login.LoginFragment
import kotlinx.coroutines.flow.StateFlow

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
            Log.i("HELLO6","user == null")
            if (user == null) {
                findNavController().navigate(R.id.loginFragment)
            }else{
                Log.i("HELLO7","user IS not null")
            }
        })

        printBackStack()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentBackStackEntry = findNavController().currentBackStackEntry!!
        val saveStateHandle = currentBackStackEntry.savedStateHandle
        saveStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL) //This execute only if the pair (key=LoginFragment.LOGIN_SUCCESSFUL, value = true or false) exist in the saveStateHandle
            .observe(currentBackStackEntry, Observer { success ->
                if(!success){
                    //This is for the case where the user has been sent to the loginfragment
                    //but he try to press the back/up button

                    //  findNavController().popBackStack() will pop up this fragment and therefor
                    //  send you to the nav-graph itself (that is the root element in the back stack)
                    // and the navgraph will send you to start destination. In this case, it's the same
                    //transaction fragment but with user info still null, it will redirect you to the logging fragment
                    findNavController().popBackStack()
                }
            })
    }

    private fun onSuscribeMsgDone(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    private fun printBackStack(){

        val backStack: StateFlow<List<NavBackStackEntry>> = findNavController().currentBackStack
        for (entry in backStack.value){
            Log.i("HELLO5",entry.destination.displayName)
        }
    }

}