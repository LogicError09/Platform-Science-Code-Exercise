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
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application

        val driver = arguments?.let { DeliveryFragmentArgs.fromBundle(it).selectedDriver }

        val viewModelFactory = DeliveryViewModelFactory(driver.toString(), application)

        deliveryViewModel = ViewModelProvider(this, viewModelFactory)[DeliveryViewModel::class.java]


        binding.viewModel = deliveryViewModel

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
       binding.unbind()
    }
}