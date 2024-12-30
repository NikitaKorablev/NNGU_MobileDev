package com.app.task6

import android.app.Application

class App: Application() {
    val deviseService: DeviceService by lazy {
        DeviceService()
    }
}