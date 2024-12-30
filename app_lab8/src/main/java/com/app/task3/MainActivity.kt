package com.app.task3

import android.annotation.SuppressLint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.databinding.ActivityMainTask3Binding
import java.lang.Math.round
import kotlin.math.*


class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityMainTask3Binding
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    override fun onResume() {
        super.onResume()

        val sensorType = Sensor.TYPE_ROTATION_VECTOR
        val sensor = if (sensorManager.getDefaultSensor(sensorType) != null) {
            sensorManager.getDefaultSensor(sensorType)
        } else null

        if (sensor != null) {
            sensorManager.registerListener(this,
                sensor,
                SensorManager.SENSOR_DELAY_UI)
        }
    }

    fun quaternionToEulerAngles(q: Quaternion): Triple<Double, Double, Double> {
        val roll = atan2(2 * (q.w * q.x + q.y * q.z), 1 - 2 * (q.x * q.x + q.y * q.y))
        val pitch = asin(2 * (q.w * q.y - q.z * q.x))
        val yaw = atan2(2 * (q.w * q.z + q.x * q.y), 1 - 2 * (q.y * q.y + q.z * q.z))

        val rollDeg = radiansToDegrees(roll)
        val pitchDeg = radiansToDegrees(pitch)
        val yawDeg = radiansToDegrees(yaw)

        return Triple(rollDeg, pitchDeg, yawDeg)
    }

    fun radiansToDegrees(radians: Double): Double {
        return radians * 180.0 / PI
    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            val x_sin = event.values[0].toDouble()
            val y_sin = event.values[1].toDouble()
            val z_sin = event.values[2].toDouble()
            val cos = event.values[3].toDouble()

            val q = Quaternion(x_sin, y_sin, z_sin, cos)
            val (rollDeg, pitchDeg, yawDeg) = quaternionToEulerAngles(q)

            binding.xyValue.text = round(rollDeg).toString()
            binding.xzValue.text = round(pitchDeg).toString()
            binding.zyValue.text = round(yawDeg).toString()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }

    // This is onPause function of our app
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}