package com.app.task6

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.databinding.ActivityMainTask6Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask6Binding
    private val adapter = DeviceAdapter()
    private val deviseService: DeviceService
        get() = (applicationContext as App).deviseService
    private lateinit var bluetoothManager: BluetoothManager
    private var bluetoothAdapter: BluetoothAdapter? = null
    private lateinit var receiver: BluetoothDeviceReceiver

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
        binding.bluetoothDevisesList.layoutManager = LinearLayoutManager(this)
        binding.bluetoothDevisesList.adapter = adapter

        bluetoothManager = getSystemService(BluetoothManager::class.java)
        bluetoothAdapter = bluetoothManager.adapter

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported",Toast.LENGTH_SHORT)
                .show()
            finish()
        }

        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        receiver = BluetoothDeviceReceiver(this::onDeviceFound)
        registerReceiver(receiver, filter)

        if (!bluetoothAdapter?.isEnabled!!) {
            Log.d("Request", "PermissionRequest")

            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        } else requestPermissions()
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.BLUETOOTH_SCAN), REQUEST_SCAN_PERMISSION)
        } else {
//            requestDiscover()
            startScanning()
        }
    }

//    private fun requestDiscover() {
//        val discoverableIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
//            putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
//        }
//        startActivityForResult(discoverableIntent, REQUEST_DISCOVERABLE_PERMISSION)
//    }

    @SuppressLint("MissingPermission")
    private fun startScanning() {
        Log.d("Scanning", "Scanning is started")
        bluetoothAdapter!!.bluetoothLeScanner.startScan(scanCallback)
        bluetoothAdapter!!.startDiscovery()
    }

    private val scanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            Log.d("ScanRes", "Found new device")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this@MainActivity, // Use context from activity
                    arrayOf(Manifest.permission.BLUETOOTH_CONNECT),
                    REQUEST_CONNECT_PERMISSION)
                return
            }

            val name: String = result.device.name?: "Unknown"
            val distance = Device.calculateDistance(result.rssi)
            val device = Device(name, distance, result.device.address)

            onDeviceFound(device)
        }
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }

    private fun onDeviceFound(device: Device) {
        Log.d("NewDevice", device.name)
        if (!deviseService.deviceExists(device.address)) {
            deviseService.addDevice(device)
            adapter.data = deviseService.getDevices()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("Request", "PermissionResult ${grantResults[0]}, Granted= ${PackageManager.PERMISSION_GRANTED}")
        if (requestCode == REQUEST_ENABLE_BT) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                requestPermissions()
            else Toast.makeText(
                this,
                "Location permission is required",
                Toast.LENGTH_SHORT).show()
        } else if (requestCode == REQUEST_SCAN_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                startScanning()
            else Toast.makeText(
                this,
                "Location permission is required",
                Toast.LENGTH_SHORT).show()
        } else if (requestCode == REQUEST_ALL_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults.all {
                it == PackageManager.PERMISSION_GRANTED
            }) startScanning()
            else Toast.makeText(
                this,
                "Необходимы разрешения для сканирования и геолокации",
                Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    override fun onDestroy() {
        super.onDestroy()
        if (bluetoothAdapter!!.isEnabled) {
            bluetoothAdapter!!.bluetoothLeScanner.stopScan(scanCallback)
            deviseService.clear()
        }
        unregisterReceiver(receiver)
    }

    companion object {
        private const val REQUEST_ENABLE_BT = 1
        private const val REQUEST_CONNECT_PERMISSION = 2
        private const val REQUEST_DISCOVERABLE_PERMISSION = 3
        private const val REQUEST_SCAN_PERMISSION = 4
        private const val REQUEST_ALL_PERMISSIONS = 5
    }
}
