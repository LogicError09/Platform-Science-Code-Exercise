package com.example.platformscienceexercise.shipment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.platformscienceexercise.databinding.FragmentDeliveryBinding

class DeliveryFragment : Fragment() {

    private lateinit var deliveryViewModel: DeliveryViewModel
    private lateinit var binding: FragmentDeliveryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDeliveryBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val driver = arguments?.let { DeliveryFragmentArgs.fromBundle(it).selectedDriver }

        val viewModelFactory = driver?.let { DeliveryViewModelFactory(it) }

        deliveryViewModel = viewModelFactory?.let { ViewModelProvider(this, it) }
            ?.get(DeliveryViewModel::class.java)!!


        binding.viewModel = deliveryViewModel

        return binding.root
    }
}