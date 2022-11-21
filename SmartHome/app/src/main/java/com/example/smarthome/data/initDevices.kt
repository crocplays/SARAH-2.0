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
            deviceDescription = "Dimmer Lights In Adi's Room"
        ),
        Device(
            deviceId = 2,
            deviceType = 2,
            deviceIcon = R.drawable.lights,
            deviceName = "Eden Ceiling Lights",
            deviceDescription = "Toggle Lights In Eden's Room"
        )


    )
}