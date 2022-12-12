package com.example.smarthome.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.smarthome.databinding.ToggleDeviceControlBinding


class ToggleDeviceControlFragment : Fragment() {
    private var _binding : ToggleDeviceControlBinding? = null
    private val binding get() = _binding!!

    //Access to chosen device in fragment
    private val args : ToggleDeviceControlFragmentArgs by navArgs()

    //  Server and Client
    private var server = (activity as MainActivity).server
    private var client = (activity as  MainActivity).client

    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ToggleDeviceControlBinding.inflate(inflater, container, false)

        val deviceName = args.device.deviceName
        val deviceDesc = args.device.deviceDescription
        val deviceId = args.device.deviceId
        val deviceIcon = args.device.deviceIcon

        val powerMode = false
        binding.textViewStatus.text = "Status: $powerMode"

        if (deviceIcon != null) {
            binding.imageViewDeviceIcon.setImageResource(deviceIcon)
        }
        binding.textViewDeviceName.text = deviceName
        binding.textViewDeviceDesc.text = deviceDesc
        
        binding.switchDevicePower.setOnCheckedChangeListener { buttonView, isChecked ->
            // do something, the isChecked will be true if the switch is in the On position
            binding.textViewStatus.text = "Status: $isChecked"
            client.write("$deviceId:$isChecked".toByteArray())
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    
}