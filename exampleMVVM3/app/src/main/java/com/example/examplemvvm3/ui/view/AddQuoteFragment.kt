package com.example.examplemvvm3.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.examplemvvm3.R
import com.example.examplemvvm3.databinding.FragmentAddQuoteBinding
import com.example.examplemvvm3.ui.viewmodel.AddQuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddQuoteFragment : Fragment() {

    companion object {
        fun newInstance() = AddQuoteFragment()
    }

    private lateinit var addQuoteBinding: FragmentAddQuoteBinding
    //private lateinit var viewModel: AddQuoteViewModel
    private val viewModel: AddQuoteViewModel by  viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addQuoteBinding=FragmentAddQuoteBinding.inflate(inflater,container,false)
        //viewModel = ViewModelProvider(this)[AddQuoteViewModel::class.java]
        return addQuoteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Observers
        viewModel.msgDone.observe(viewLifecycleOwner, Observer {
            onMsgDoneSuscribe(it)
        })

        addQuoteBinding.btAddQuote.setOnClickListener {
            val quote = addQuoteBinding.addQuoteEditText.text.toString()
            val author = addQuoteBinding.addAuthorEditText.text.toString()
            viewModel.addNewQuote(quote, author)
            addQuoteBinding.addQuoteEditText.text?.clear()
            addQuoteBinding.addAuthorEditText.text?.clear()
        }
    }

    private fun onMsgDoneSuscribe(msg: String?) {
        if(msg!=null) Toast.makeText(requireContext(),msg,Toast.LENGTH_SHORT).show()
    }

}