package com.example.smarthome.data

import androidx.annotation.DrawableRes

class Device(
    var deviceId : Long,
    var deviceType : Int ,
    @DrawableRes
    var deviceIcon : Int? ,
    var deviceName : String ,
    var deviceDescreption : String

    ) {



}