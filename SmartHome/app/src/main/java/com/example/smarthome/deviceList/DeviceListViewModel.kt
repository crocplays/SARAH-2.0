package com.example.smarthome.deviceList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smarthome.data.DataSource
import com.example.smarthome.data.Device
import kotlin.random.Random

class DeviceListViewModel(val dataSource: DataSource) : ViewModel() {

    val deviceLiveData = dataSource.getDeviceList()

    /* If the name and description are present, create new Device and add it to the datasource */
    fun insertDevice(deviceName: String?, deviceDescription: String?) {
        if (deviceName == null || deviceDescription == null) {
            return
        }

        val image = dataSource.getRandomDeviceImageAsset()
        val newDevice = Device(
            Random.nextLong(),
            1,
            image,
            deviceName,
            deviceDescription
        )

        dataSource.addDevice(newDevice)
    }
}

class DevicesListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeviceListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DeviceListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}