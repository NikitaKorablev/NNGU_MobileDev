package com.app.task6

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BluetoothDeviceReceiver(private val onDeviceFound: (device: Device) -> Unit): BroadcastReceiver() {
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context, intent: Intent) {
        val action: String = intent.action.toString()
        when(action) {
            BluetoothDevice.ACTION_FOUND -> {
                Log.d("ACTION_FOUND", "Found new device")
                val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                val rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE).toInt()

                val distance = Device.calculateDistance(rssi)
                val newDevice = Device(device!!.name ?: "Unknown", distance, device.address)
                onDeviceFound(newDevice)
            }
        }
    }
}