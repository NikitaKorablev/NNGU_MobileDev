package com.app.task3

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
import kotlin.math.max

class FountainView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private var paint: Paint = Paint()
    private var particles: MutableList<Particle> = ArrayList()
    private var random: Random = Random()
    private var generation = false

    private companion object {
        var startX: Float = 0F
        var startY: Float = 0F
    }

    private var limitX: Float = 0F
    private var limitY: Float = 0F

    val randomParam: () -> Float = {
        var randomFloat: Float
        do {
            randomFloat = random.nextFloat() * 2 - 1
        } while (randomFloat == 0f)
        randomFloat
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        limitY = h.toFloat() / 4
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
            if (particle.alpha <= 0) {
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

    private inner class Particle(var x: Float, var y: Float) {
        val color: Int = setParticleColor()
        val size: Float = 10F
        var alpha: Float = 255F
        val alphaStep: Float = 3F
        val stX = startX
        val stY = startY
        val c: Float = randomParam()

        val dx: Float = limitX / 50

        fun update() {
            x = if (c < 0) x+dx else x-dx

            val den = c * limitX
            val xx0 = x-stX
            y = limitY*xx0*xx0/(den*den) + 2*limitY*xx0/den + stY

            alpha -= alphaStep
            paint.alpha = alpha.toInt()

            if (alpha < 0F) alpha = 0F
        }
    }
}
