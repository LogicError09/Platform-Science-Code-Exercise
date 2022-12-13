package com.example.platformscienceexercise.shipment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.platformscienceexercise.model.SelectedDriver

class DeliveryViewModelFactory(private val driver: SelectedDriver) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveryViewModel::class.java)) {
            return DeliveryViewModel(driver) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}