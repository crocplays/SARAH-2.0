package com.example.smarthome.deviceList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.data.Device

class DeviceAdapter (private val onClick: (Device) -> Unit) :
    ListAdapter<Device, DeviceAdapter.DeviceViewHolder>(DeviceDiffCallback) {


    /* ViewHolder for Device, takes in the inflated view and the onClick behavior. */
    class DeviceViewHolder(itemView: View, val onClick: (Device) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val deviceTitleTextView: TextView = itemView.findViewById(R.id.item_title)
        private val deviceDescTextView: TextView = itemView.findViewById(R.id.item_description)
        private val deviceImageView: ImageView = itemView.findViewById(R.id.item_image)
        private var currentDevice: Device? = null

        init {
            itemView.setOnClickListener {
                currentDevice?.let {
                    onClick(it)
                }
            }
        }

        /* Bind device name, title and image. */
        fun bind(device: Device) {
            currentDevice = device

            deviceTitleTextView.text = device.deviceName
            deviceDescTextView.text = device.deviceDescreption

            if (device.deviceIcon != null) {
                deviceImageView.setImageResource(device.deviceIcon!!)
            } else {
                deviceImageView.setImageResource(R.drawable.lights)
            }
        }
    }


    /* Creates and inflates view and return DeviceViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.device_item_layout, parent, false)
        return DeviceViewHolder(view, onClick)
    }

    /* Gets current Device and uses it to bind view. */
    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = getItem(position)
        holder.bind(device)
    }
}

object DeviceDiffCallback : DiffUtil.ItemCallback<Device>() {
    override fun areItemsTheSame(oldItem: Device, newItem: Device): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Device, newItem: Device): Boolean {
        return oldItem.deviceId == newItem.deviceId
    }
}
