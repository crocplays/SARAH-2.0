package com.example.smarthome

import android.content.res.Resources
import com.example.smarthome.data.Device

fun deviceList(resources: Resources): List<Device>{
    return listOf(
        Device(
            deviceId = 1,
            deviceType = 1,
            deviceIcon = R.drawable.lights,
            deviceName = "Adi Ceiling Lights",
            deviceDescreption = "Dimmer Lights In Adi's Room"
        )


    )
}