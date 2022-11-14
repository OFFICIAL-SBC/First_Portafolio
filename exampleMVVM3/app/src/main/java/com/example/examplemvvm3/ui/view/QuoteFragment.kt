package com.example.examplemvvm3.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.examplemvvm3.R
import com.example.examplemvvm3.databinding.FragmentQuoteBinding
import com.example.examplemvvm3.domain.model.Quote
import com.example.examplemvvm3.ui.viewmodel.QuoteFragmentViewModel

class QuoteFragment : Fragment() {

    companion object {
        fun newInstance() = QuoteFragment()
    }

    private lateinit var quoteFragmentBinding: FragmentQuoteBinding
    //private lateinit var viewModel: QuoteFragmentViewModel
    private val viewModel: QuoteFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        quoteFragmentBinding=FragmentQuoteBinding.inflate(inflater,container,false)
        //viewModel = ViewModelProvider(this)[QuoteFragmentViewModel::class.java]
        return quoteFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onCreate()


        //Observers
        viewModel.quoteModelLiveData.observe(viewLifecycleOwner, Observer { quote ->
            onQuoteDoneSuscribe(quote)
        })

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, Observer { flag ->
            onFlagDoneSuscribe(flag)
        })

        quoteFragmentBinding.ViewContainerFragment.setOnClickListener {
            viewModel.randomQuote()
        }

    }

    private fun onFlagDoneSuscribe(flag: Boolean?) {
        if (flag != null){
            when(flag){
                true -> quoteFragmentBinding.progressBar.visibility=VISIBLE
                else -> quoteFragmentBinding.progressBar.visibility=GONE
            }
        }
    }

    private fun onQuoteDoneSuscribe(quote: Quote?) {
        if (quote != null) {
            quoteFragmentBinding.tvQuote.text=quote.quote
            quoteFragmentBinding.tvAuthor.text= quote.author
        }
    }

}