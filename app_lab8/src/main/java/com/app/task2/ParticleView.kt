package com.app.task2

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.Random
import kotlin.math.cos
import kotlin.math.sin

class ParticleView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private var paint: Paint? = null
    private var particles: MutableList<Particle>? = null
    private var random: Random? = null
    private val velocity: Float = 5F

    init {
        paint = Paint()
        particles = ArrayList()
        random = Random()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Обновление и рисование частиц
        val iterator = particles!!.iterator()
        while (iterator.hasNext()) {
            val particle = iterator.next()
            particle.update()
            paint!!.color = particle.color
            canvas.drawCircle(particle.x, particle.y, particle.size, paint!!)
            if (particle.alpha <= 0) {
                iterator.remove() // Удаление частиц, которые исчезли
            }
        }

        // Повторный вызов onDraw
        if (particles!!.isNotEmpty()) {
            invalidate()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            generateParticles(event.x, event.y)
        }
        return super.onTouchEvent(event)
    }

    private fun generateParticles(x: Float, y: Float) {
        for (i in 0..99) {
            particles!!.add(Particle(x, y, random!!))
        }
        invalidate() // Обновление экрана
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

    private inner class Particle(var x: Float, var y: Float, random: Random) {
        var color: Int = setParticleColor()
        var size: Float = 15F
        var alpha: Int = 255
        val randomAngle: Float = random.nextFloat() * 360

        fun update() {
            val dx = if (randomAngle > 90 && randomAngle < 270)
                -velocity * cos(randomAngle)
                else velocity * cos(randomAngle)
            val dy = if (randomAngle > 180 && randomAngle < 360)
                -velocity * sin(randomAngle)
                else velocity * sin(randomAngle)

            x += dx
            y += dy
            alpha -= 5
            size -= 0.3f
            paint!!.alpha = alpha
            if (alpha < 0) alpha = 0
            if (size < 0) size = 0f
        }
    }
}
