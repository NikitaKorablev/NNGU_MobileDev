package com.app_lab6.task4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TrafficLightView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var job: Job? = null
    private var trafficIsStarted = false

    private val radius = 100
    private val gap = 10

    private val redPaint = Paint().apply {
        color = Color.RED
    }
    private val yellowPaint = Paint().apply {
        color = Color.YELLOW
    }
    private val greenPaint = Paint().apply {
        color = Color.GREEN
    }
    private val blackPaint = Paint().apply {
        color = Color.BLACK
    }

    fun start() {
        trafficIsStarted = true
        job = CoroutineScope(Dispatchers.Main).launch {
            while(trafficIsStarted) {
                switchLights("red")
                delay(3000)
                switchLights("yellow")
                delay(1000)
                switchLights("green")
                delay(3000)
                switchLights("yellow")
                delay(1000)
            }
        }
    }

    fun stop() {
        trafficIsStarted = false
        job?.cancel()
    }

    private fun switchLights(light: String) {
        currentLight = LightState.GREEN

        when (light) {
            "red" -> currentLight = LightState.RED
            "yellow" -> currentLight = LightState.YELLOW
            "green" -> currentLight = LightState.GREEN
        }
    }

    private var currentLight: LightState = LightState.RED
        set(value) {
            field = value
            invalidate()
        }

    enum class LightState {
        RED, YELLOW, GREEN
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Рисуем светофор с учетом текущего состояния
        val width = radius.toFloat()
        val height = radius.toFloat()

        when (currentLight) {
            LightState.RED -> {
                canvas.drawCircle(width, height, radius.toFloat(), redPaint)
                canvas.drawCircle(width, height*3 + gap.toFloat(), radius.toFloat(), blackPaint)
                canvas.drawCircle(width, height*5 + gap.toFloat()*2, radius.toFloat(), blackPaint)
            }
            LightState.YELLOW -> {
                canvas.drawCircle(width, height, radius.toFloat(), blackPaint)
                canvas.drawCircle(width, height*3 + gap.toFloat(), radius.toFloat(), yellowPaint)
                canvas.drawCircle(width, height*5 + gap.toFloat()*2, radius.toFloat(), blackPaint)
            }
            LightState.GREEN -> {
                canvas.drawCircle(width, height, radius.toFloat(), blackPaint)
                canvas.drawCircle(width, height*3 + gap.toFloat(), radius.toFloat(), blackPaint)
                canvas.drawCircle(width, height*5 + gap.toFloat()*2, radius.toFloat(), greenPaint)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Устанавливаем фиксированный размер, например 300x600 пикселей
        val desiredWidth = radius * 2
        val desiredHeight = radius*6 + gap*3

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize // Если EXACTLY, используем заданный размер
            MeasureSpec.AT_MOST -> minOf(desiredWidth, widthSize) // При AT_MOST используем минимальный возможный размер
            else -> desiredWidth // В противном случае используем желаемый размер
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> minOf(desiredHeight, heightSize)
            else -> desiredHeight
        }

        setMeasuredDimension(width, height)
    }
}
