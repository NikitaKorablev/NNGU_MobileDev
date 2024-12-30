package com.app.task6

import kotlin.math.pow

data class Device(val name: String, val distance: Double, val address: String) {
    companion object {
        fun calculateDistance(rssi: Int, referenceTxPower: Int = -59): Double {
            if (rssi == 0) return -1.0
            val pathLossExponent = 3.0

            val ratio: Double = rssi * 1.0 / referenceTxPower
            if (ratio < 1.0) {
                return ratio.pow(10.0)
            } else {
                val distance: Double = 10.0.pow((referenceTxPower - rssi) / (10 * pathLossExponent))
                return distance
            }
        }
    }
}
