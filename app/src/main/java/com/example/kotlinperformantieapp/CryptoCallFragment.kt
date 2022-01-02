package com.example.kotlinperformantieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinperformantieapp.databinding.FragmentCryptoCallBinding


class CryptoCallFragment : Fragment() {

    private val viewModel: CryptoCallViewModel by lazy {
        ViewModelProvider(this).get(CryptoCallViewModel::class.java)
    }

    private lateinit var binding : FragmentCryptoCallBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCryptoCallBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

//        zonderCoroutine()
        metCoroutine()

//        listView!!.adapter = NetwerkDataAdapter(this.requireContext(), viewModel.responseMultiple.value as ArrayList<String>)



//        // Inflate the layout for this fragment
//        jobCoScopeVanX.launch(Dispatchers.IO) {
//        }
        return binding.root
    }

    fun zonderCoroutine(){
        viewModel.callRunNetCall()
    }

    fun metCoroutine(){
        viewModel.callRunNetCallCoroutine()
    }


}