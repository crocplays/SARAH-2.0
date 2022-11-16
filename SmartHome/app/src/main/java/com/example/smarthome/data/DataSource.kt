package com.example.smarthome.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smarthome.deviceList

class DataSource(resources: Resources) {
    private val initialDeviceList = deviceList(resources)
    private val deviceLiveData = MutableLiveData(initialDeviceList)

    /* Adds device to liveData and posts value. */
    fun addDevice(device: Device) {
        val currentList = deviceLiveData.value
        if (currentList == null) {
            deviceLiveData.postValue(listOf(device))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, device)
            deviceLiveData.postValue(updatedList)
        }
    }

    /* Removes device from liveData and posts value. */
    fun removeDevice(device: Device) {
        val currentList = deviceLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(device)
            deviceLiveData.postValue(updatedList)
        }
    }

    /* Returns device given an ID. */
    fun getDeviceForId(id: Long): Device? {
        deviceLiveData.value?.let { devices ->
            return devices.firstOrNull{ it.deviceId == id}
        }
        return null
    }

    fun getDeviceList(): LiveData<List<Device>> {
        return deviceLiveData
    }

    /* Returns a random flower asset for flowers that are added. */
    fun getRandomDeviceImageAsset(): Int? {
        val randomNumber = (initialDeviceList.indices).random()
        return initialDeviceList[randomNumber].deviceIcon
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }



}