package com.example.smarthome.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.data.DataSource
import com.example.smarthome.data.Device
import com.example.smarthome.databinding.ActivityMainBinding
import com.example.smarthome.deviceList.DeviceAdapter
import com.example.smarthome.deviceList.DeviceListViewModel
import com.example.smarthome.deviceList.DevicesListViewModelFactory

const val DEVICE_ID = "device id"

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    //Recycler View
//    private val newDeviceActivityRequestCode = 1
//    private val deviceListViewModel by viewModels<DeviceListViewModel> {
//        DevicesListViewModelFactory(this)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set status bar to black
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.black)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        /* Instantiates DeviceAdapter.  adapters are added to concatAdapter.
            which displays the contents sequentially */
//        val deviceAdapter = DeviceAdapter { device -> adapterOnClick(device) }
//        val recyclerView: RecyclerView = findViewById(R.id.device_list_recycler_view)
//        recyclerView.adapter = deviceAdapter
//
//        deviceListViewModel.deviceLiveData.observe(this) {
//            it?.let {
//                deviceAdapter.submitList(it as MutableList<Device>)
//            }
//        }


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    /* Opens DeviceControlFragment when RecyclerView item is clicked. */
//    private fun adapterOnClick(device: Device) {
//
//    }

}