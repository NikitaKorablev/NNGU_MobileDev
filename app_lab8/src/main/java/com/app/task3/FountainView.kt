package com.app.task3

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import java.util.Random
import kotlin.math.*

class FountainView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs), SensorEventListener {
    private var paint: Paint = Paint()
    private var particles: MutableList<Particle> = ArrayList()
    private var random: Random = Random()
    private var generation = false

    private var limitX: Float = 0F
    private var limitY: Float = 0F

    private var width: Float = 0f
    private var height: Float = 0f

    private val sensorManager: SensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var deviationXZ = 0f
    private var deviationXY = 0f

    companion object {
        var startX = 0f
        var startY = 0f
    }

    val randomAngle: () -> Float = {
        var randomFloat: Float
        do {
            randomFloat = (random.nextFloat() * PI/3 + PI/3).toFloat()
        } while (randomFloat == 0f)
        randomFloat
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w.toFloat()
        height = h.toFloat()
        limitY = height / 4
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Обновление и рисование частиц
        val iterator = particles.iterator()
        while (iterator.hasNext()) {
            val particle = iterator.next()
            particle.update()
            paint.color = particle.color
            canvas.drawCircle(particle.x, particle.y, particle.size, paint)
//            if (particle.alpha <= 0) {
//                iterator.remove() // Удаление частиц, которые исчезли
//            }
            if (particle.x < 0 || particle.x > width || particle.y < 0 || particle.y > height) {
                iterator.remove() // Удаление частиц, которые исчезли
            }
        }

        if (generation) generateParticles(startX, startY)

        if (particles.isNotEmpty()) {
            invalidate()
        }
    }

    private fun generateParticles(x: Float, y: Float) {
        for (i in 0 until 10) {
            particles.add(0, Particle(x, y))
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                startX = event.x
                startY = event.y
                limitX = max(width-startX, startX)
                generation = true
                generateParticles(startX, startY)
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> {
                generation = false
                invalidate()
                return true
            }
        }

        return super.onTouchEvent(event)
    }

    private fun setParticleColor(): Int {
        val nightModeFlags = context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        return when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> Color.WHITE
            Configuration.UI_MODE_NIGHT_NO -> Color.BLACK
            else -> Color.GRAY
        }
    }

    private inner class Particle(val x0: Float, val y0: Float) {
        val color: Int = setParticleColor()
        val size: Float = 10F
        var alpha: Float = 255F
        val alphaStep: Float = 3F
        var x: Float = x0
        var y: Float = y0
        val speed = 100F
        val angle: Float = randomAngle()
        var time = 0F

        fun update() {
            x = x0 + speed*cos(angle+deviationXZ)*time
            y = y0 - (speed*sin(angle+deviationXZ)*time - 9.8*deviationXY*time*time/2).toFloat()
            time += .4f

            alpha -= alphaStep
            paint.alpha = alpha.toInt()

            if (alpha < 0F) alpha = 0F
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

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

    private fun quaternionToEulerAngles(q: Quaternion): Triple<Double, Double, Double> {
        val roll = atan2(2 * (q.w * q.x + q.y * q.z), 1 - 2 * (q.x * q.x + q.y * q.y))
        val pitch = asin(2 * (q.w * q.y - q.z * q.x))
        val yaw = atan2(2 * (q.w * q.z + q.x * q.y), 1 - 2 * (q.y * q.y + q.z * q.z))

        val rollDeg = radiansToDegrees(roll)
        val pitchDeg = radiansToDegrees(pitch)
        val yawDeg = radiansToDegrees(yaw)

        return Triple(rollDeg, pitchDeg, yawDeg)
    }

    private fun radiansToDegrees(radians: Double): Double {
        return radians * 180.0 / PI
    }

    private fun devToPer(dev: Float): Double {
        return (dev/90).toDouble()
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

            deviationXZ = (-pitchDeg*PI/180).toFloat()
            deviationXY = devToPer(rollDeg.toFloat()).toFloat()
        }
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    // This is onPause function of our app
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        sensorManager.unregisterListener(this)
    }
}
