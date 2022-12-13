package com.example.platformscienceexercise.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.platformscienceexercise.databinding.FragmentOverviewBinding
import com.example.platformscienceexercise.overview.adapter.OverviewListAdapter

class OverviewFragment : Fragment() {

    private lateinit var overviewViewModel: OverviewViewModel
    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val application = requireNotNull(this.activity).application

        val viewModelFactory = OverviewViewModelFactory(application)

        overviewViewModel = ViewModelProvider(this, viewModelFactory)[OverviewViewModel::class.java]

        binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = overviewViewModel

        binding.driversList.adapter = OverviewListAdapter(OverviewListAdapter.OnClickListener {
            overviewViewModel.displayDeliveryDriver(it)
        })

        overviewViewModel.navigateToSelectedDriver.observe(viewLifecycleOwner) {
            if (it != null) {
                this.findNavController().navigate(OverviewFragmentDirections.actionShowDelivery(it))
                overviewViewModel.displayUserDetailsComplete()
            }
        }

        return binding.root
    }
}