package com.example.platformscienceexercise.shipment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DeliveryViewModelFactory(private val driver: String, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveryViewModel::class.java)) {
            return DeliveryViewModel(driver, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}