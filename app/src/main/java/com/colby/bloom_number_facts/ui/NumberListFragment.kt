package com.colby.bloom_number_facts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.colby.bloom_number_facts.databinding.NumberListFragmentBinding

class NumberListFragment : Fragment() {

    private lateinit var binding: NumberListFragmentBinding
    private lateinit var viewModel: NumberCollectorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NumberListFragmentBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[NumberCollectorViewModel::class.java]



        return binding.root
    }
}