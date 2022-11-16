package com.example.smarthome.deviceList

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.data.Device
import com.example.smarthome.databinding.MainMenuFragmentBinding
import java.util.*


/**
 * First Fragment - Main Menu
 */


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

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        _binding = MainMenuFragmentBinding.inflate(inflater, container, false)
        binding.textView.text = todMessage()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }


    override fun onResume() {
        super.onResume()
        binding.textView.text = todMessage()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}