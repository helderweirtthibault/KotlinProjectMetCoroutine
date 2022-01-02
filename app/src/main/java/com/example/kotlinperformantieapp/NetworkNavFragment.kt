package com.example.kotlinperformantieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.kotlinperformantieapp.databinding.FragmentNetworkNavBinding


class NetworkNavFragment : Fragment() {

    private lateinit var binding: FragmentNetworkNavBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentNetworkNavBinding.inflate(inflater)

        binding.apiCoButton.setOnClickListener{ view ->
            Navigation.findNavController(view).navigate(R.id.action_networkNavFragment_to_networkCoroutineCallFragment);
        }
        binding.cyptoButton.setOnClickListener{ view ->
            Navigation.findNavController(view).navigate(R.id.action_networkNavFragment_to_cryptoCallFragment);
        }
        return binding.root
    }

}