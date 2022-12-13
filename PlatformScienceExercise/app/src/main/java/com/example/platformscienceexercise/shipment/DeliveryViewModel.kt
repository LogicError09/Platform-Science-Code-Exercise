package com.example.platformscienceexercise.shipment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.platformscienceexercise.model.SelectedDriver

class DeliveryViewModel(driver: SelectedDriver) : ViewModel() {

    private val _selectedDriver = MutableLiveData<SelectedDriver>()

    val selectedDriver: LiveData<SelectedDriver>
        get() = _selectedDriver

    init {
        _selectedDriver.value = driver
    }
}