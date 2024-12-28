package com.app.task6

import com.github.javafaker.Faker

class DeviseService {
    private var devises = mutableListOf<Devise>()


    fun getDevises(): List<Devise> {
        return devises.toList()
    }

    fun scanDevice(adapter: DeviseAdapter) {

    }
}