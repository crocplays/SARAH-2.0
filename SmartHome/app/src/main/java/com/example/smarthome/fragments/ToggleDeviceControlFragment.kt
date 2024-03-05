package com.example.smarthome.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.smarthome.R
import com.example.smarthome.communication.UdpClient
import com.example.smarthome.databinding.ToggleDeviceControlBinding


class ToggleDeviceControlFragment : Fragment() {
    private var _binding : ToggleDeviceControlBinding? = null
    private val binding get() = _binding!!

    //Access to chosen device in fragment
    private val args : ToggleDeviceControlFragmentArgs by navArgs()

    //  Server and Client
    //private var server = (activity as MainActivity).server
    //private var client = (activity as  MainActivity).client
    private var serverIp = ""
    private var serverPort = 0

    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ToggleDeviceControlBinding.inflate(inflater, container, false)

        val deviceName = args.device.deviceName
        val deviceDesc = args.device.deviceDescription
        val deviceId = args.device.deviceId
        val deviceIcon = args.device.deviceIcon
        this.serverIp = resources.getString(R.string.server_ip)
        this.serverPort = resources.getInteger(R.integer.server_port)


        val powerMode = false
        binding.textViewStatus.text = "Status: $powerMode"

        if (deviceIcon != null) {
            binding.imageViewDeviceIcon.setImageResource(deviceIcon)
        }
        binding.textViewDeviceName.text = deviceName
        binding.textViewDeviceDesc.text = deviceDesc
        
        binding.switchDevicePower.setOnCheckedChangeListener { buttonView, isChecked ->
            // do something, the isChecked will be true if the switch is in the On position
            try {
                val payload = "$deviceId toggle ${isChecked.toString()}"
                UdpClient.sendDataToServer(payload, serverIp, serverPort) { response ->
                    binding.textViewStatus.text = "Status: $response"
                }
            }
            catch (e: Exception){
                binding.textViewStatus.text = "Status: ${e.message}"
            }
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        this.serverIp = resources.getString(R.string.server_ip)
//        this.serverPort = resources.getInteger(R.integer.server_port)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    
}