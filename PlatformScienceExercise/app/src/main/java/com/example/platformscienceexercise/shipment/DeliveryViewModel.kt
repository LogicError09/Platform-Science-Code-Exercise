package com.example.platformscienceexercise.shipment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platformscienceexercise.model.DeliveryData
import com.example.platformscienceexercise.repository.DeliveryDataRepository
import kotlinx.coroutines.launch
import java.io.IOException

enum class ShipmentDataStatus { LOADED, ERROR }

class DeliveryViewModel(driver: String, application: Application) : ViewModel() {

    private val repository = DeliveryDataRepository(application)

    private val _selectedDriver = MutableLiveData<String>()

    val selectedDriver: LiveData<String>
        get() = _selectedDriver

    private val _assignedAddress = MutableLiveData<String>()

    val assignedAddress: LiveData<String>
        get() = _assignedAddress

    private val _status = MutableLiveData<ShipmentDataStatus>()

    val status: LiveData<ShipmentDataStatus>
        get() = _status


    init {
        _selectedDriver.value = driver
        loadShipmentInfoFromRepo()
    }

    private fun loadShipmentInfoFromRepo() {
        viewModelScope.launch {
            val data = repository.deliveryData
            try {
                _assignedAddress.value = getAssignedAddress(data)
                _status.value = ShipmentDataStatus.LOADED

            } catch (error: IOException) {
                _status.value = ShipmentDataStatus.ERROR
            }
        }
    }

    private fun getAssignedAddress(deliveryData: DeliveryData): String {
        return deliveryData.shipments!![1]
    }
}