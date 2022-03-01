package com.colby.bloom_number_facts.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.colby.bloom_number_facts.data.NumberFact
import com.colby.bloom_number_facts.databinding.NumberListFragmentBinding

class NumberListFragment : Fragment() {

    private lateinit var binding: NumberListFragmentBinding
    private lateinit var viewModel: RandomNumberFactsViewModel
    private lateinit var adapter: RandomNumberFactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NumberListFragmentBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[RandomNumberFactsViewModel::class.java]
        setupFactList()
        setupUiStateListener()

        return binding.root
    }

    private fun setupFactList() {
        adapter = RandomNumberFactsAdapter()
        binding.numberList.layoutManager = LinearLayoutManager(context)
        binding.numberList.adapter = adapter
    }

    private fun setupUiStateListener() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            updateList(uiState.isLoading, uiState.numberFacts ?: emptyList())
            displayError(uiState.errorMessage)
        }
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            viewModel.errorShown()
        }
    }

    private fun updateList(isLoading: Boolean, numbers: List<NumberFact>) {
        if (isLoading) {
            binding.loadingBar.visibility = View.VISIBLE
            binding.numberList.visibility = View.GONE
        } else {
            binding.loadingBar.visibility = View.GONE
            binding.numberList.visibility = View.VISIBLE
            adapter.submitList(numbers)
            Log.i("fragment", numbers.toString())
        }
    }
}