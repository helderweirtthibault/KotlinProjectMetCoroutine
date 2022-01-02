package com.example.kotlinperformantieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinperformantieapp.databinding.FragmentNetworkCallBinding


class NetworkCallFragment : Fragment() {

    private val viewModel: NetworkCallViewModel by lazy {
        ViewModelProvider(this).get(NetworkCallViewModel::class.java)
    }

    private lateinit var binding : FragmentNetworkCallBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNetworkCallBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.callWithCoroutine()
        return binding.root
    }


}