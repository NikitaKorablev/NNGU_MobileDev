package com.app.task6

class DeviceService {
    private var deviсes = mutableListOf<Device>()

    fun getDevices(): List<Device> {
        return deviсes.toList()
    }

    fun deviceExists(address: String): Boolean {
        return deviсes.any { it.address == address }
    }

    fun addDevice(device: Device) {
        deviсes.add(device)
    }

    fun clear() {
        deviсes.clear()
    }
}