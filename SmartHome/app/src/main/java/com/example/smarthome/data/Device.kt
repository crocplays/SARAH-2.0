package com.example.smarthome.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Device(
    var deviceId : Long,
    var deviceType : Int ,
    @DrawableRes
    var deviceIcon : Int? ,
    var deviceName : String ,
    var deviceDescription : String

    ):Parcelable {



}