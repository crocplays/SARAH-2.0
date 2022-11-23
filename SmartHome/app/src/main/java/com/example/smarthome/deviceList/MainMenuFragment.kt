package com.example.smarthome.deviceList

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.data.Device
import com.example.smarthome.databinding.MainMenuFragmentBinding
import com.example.smarthome.fragments.MainActivity
import com.example.smarthome.data.DataSource
import com.example.smarthome.fragments.DimmerDeviceControlFragmentDirections
import java.util.*



/**
 * First Fragment - Main Menu
 */

const val DEVICE_ID = "device id"

class MainMenuFragment : Fragment() {

    private fun todMessage():String{
        val cal = Calendar.getInstance()
        val timeOfDay = cal.get(Calendar.HOUR_OF_DAY)

        return when(timeOfDay){
            in 0..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            in 17..20 -> "Good Evening"
            in 21..23 -> "Good Night"
            else -> "Error"
        }
    }



    private var _binding: MainMenuFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var tod : String = todMessage()
    //Recyclerview
//    private var layoutManager: RecyclerView.LayoutManager? = null
//    private var adapter: RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>? = null
//
    private val newDeviceActivityRequestCode = 1
    private val  deviceListViewModel by viewModels<DeviceListViewModel> {
        try {
            DevicesListViewModelFactory(requireContext())
            
        }
        catch (e : IllegalStateException ){

            tod = "requireContext Exception"
            throw e
        }

    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        //Inflate layout for recyclerview
        inflater.inflate(R.layout.main_menu_fragment, container, false)

        _binding = MainMenuFragmentBinding.inflate(inflater, container, false)
        //Time of day message
        //binding.textView.text = tod

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* Instantiates DeviceAdapter.  adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter()
        val deviceAdapter = DeviceAdapter { device -> adapterOnClick(device) }
        val concatAdapter = ConcatAdapter(headerAdapter, deviceAdapter)

        binding.deviceListRecyclerView.adapter = concatAdapter
        binding.deviceListRecyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)

        deviceListViewModel.deviceLiveData.observe(viewLifecycleOwner) {
            it?.let {
                deviceAdapter.submitList(it as MutableList<Device>)
                headerAdapter.updateHeaderText(tod)
            }
        }



        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }


    override fun onResume() {
        super.onResume()
        //binding.textView.text = tod
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /* Opens DeviceControlFragment when RecyclerView item is clicked. */
    private fun adapterOnClick(device: Device) {
        try {
        when(device.deviceType) {
            1 -> {  //dimmer type
                val action = MainMenuFragmentDirections.actionFirstFragmentToDimmerDeviceFragment(device)
                findNavController().navigate(action)}

            2 -> { //toggle type
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

            3 -> {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

            else -> {
                throw Exception("device type error")
            }
        }
        }
        catch (e : Exception){

        }
    }
}