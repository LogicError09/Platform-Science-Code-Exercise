package com.example.platformscienceexercise.overview

import android.app.Application
import androidx.lifecycle.*
import com.example.platformscienceexercise.model.DeliveryData
import com.example.platformscienceexercise.repository.DeliveryDataRepository
import kotlinx.coroutines.launch
import java.io.IOException

enum class DataStatus { LOADED, ERROR }

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DeliveryDataRepository(application)

    private val _status = MutableLiveData<DataStatus>()

    val status: LiveData<DataStatus>
        get() = _status

    private val _deliveryData = MutableLiveData<DeliveryData>()

    val deliveryData: LiveData<DeliveryData>
        get() = _deliveryData

    private val _navigateToSelectedDriver = MutableLiveData<String?>()

    val navigateToSelectedDriver: LiveData<String?>
        get() = _navigateToSelectedDriver

    init {
        loadDataFromRepository()
    }

    private fun loadDataFromRepository() {
        viewModelScope.launch {
            val data = repository.deliveryData
            try {
                _deliveryData.value = data
                _status.value = DataStatus.LOADED

            } catch (error: IOException) {
                _status.value = DataStatus.ERROR
            }
        }
    }

    fun displayDeliveryDriver(driver: String) {
        _navigateToSelectedDriver.value = driver
    }

    fun displayUserDetailsComplete() {
        _navigateToSelectedDriver.value = null
    }
}