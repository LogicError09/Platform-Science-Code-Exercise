package com.example.platformscienceexercise.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.platformscienceexercise.model.DeliveryData
import com.example.platformscienceexercise.model.SelectedDriver
import com.example.platformscienceexercise.repository.DeliveryDataRepository
import com.example.platformscienceexercise.utils.Utils
import kotlinx.coroutines.launch
import java.io.IOException

enum class DataStatus { LOADED, ERROR }

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DeliveryDataRepository(application)

    private val _status = MutableLiveData<DataStatus>()

    val status: LiveData<DataStatus>
        get() = _status

    private val _drivers = MutableLiveData<List<SelectedDriver>>()

    val drivers: LiveData<List<SelectedDriver>>
        get() = _drivers

    private val _navigateToSelectedDriver = MutableLiveData<SelectedDriver?>()

    val navigateToSelectedDriver: LiveData<SelectedDriver?>
        get() = _navigateToSelectedDriver

    init {
        loadDataFromRepository()
    }

    private fun loadDataFromRepository() {
        viewModelScope.launch {
            val data = repository.deliveryData
            try {
                _drivers.value = setShipments(data)
                _status.value = DataStatus.LOADED

            } catch (error: IOException) {
                _status.value = DataStatus.ERROR
            }
        }
    }

    private fun setShipments(deliveryData: DeliveryData): List<SelectedDriver> {
        val selectedDrivers = ArrayList<SelectedDriver>()
        deliveryData.drivers?.forEach {
            selectedDrivers.add(SelectedDriver(it,
                deliveryData.shipments?.let { it1 -> getDestinationForDriver(it, it1) }))
        }
        return selectedDrivers
    }
    private fun getDestinationForDriver(driver: String, destinations: List<String>): String? {
        return Utils().getDestinationAddressForDriver(driver, destinations)
    }

    fun displayDeliveryDriver(selectedDriver: SelectedDriver) {
        _navigateToSelectedDriver.value = selectedDriver
    }

    fun displayUserDetailsComplete() {
        _navigateToSelectedDriver.value = null
    }
}