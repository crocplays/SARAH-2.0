package com.example.smarthome.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.smarthome.databinding.DimmerDeviceControlBinding


class DimmerDeviceControlFragment : Fragment() {
    private var _binding : DimmerDeviceControlBinding? = null
    private val binding get() = _binding!!

    //Access to chosen device in fragment
    private val args : DimmerDeviceControlFragmentArgs by navArgs()

    //  Server and Client
    //private var server = (activity as MainActivity).server
    //private var client = (activity as  MainActivity).client


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DimmerDeviceControlBinding.inflate(inflater, container, false)

        val deviceName = args.device.deviceName
        val deviceDesc = args.device.deviceDescription
        val deviceId = args.device.deviceId
        val deviceIcon = args.device.deviceIcon


        val powerLevel = binding.seekBarPowerLevel.progress
        binding.textViewPowerLevel.text = "Power level is at: $powerLevel"

        if (deviceIcon != null) {
            binding.imageViewDeviceIcon.setImageResource(deviceIcon)
        }
        binding.textViewDeviceName.text = deviceName
        binding.textViewDeviceDesc.text = deviceDesc


        binding.seekBarPowerLevel.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // here, you react to the value being set in seekBar
                binding.textViewPowerLevel.text = "Power level is at: $progress"
                (activity as MainActivity).client.write(("$deviceId:$progress").toByteArray())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })





        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}