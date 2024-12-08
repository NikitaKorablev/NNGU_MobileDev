package com.app.task1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class HouseView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private var paint: Paint? = null
    private var path: Path? = null

    init {
        paint = Paint()
        paint!!.color = Color.BLACK
        paint!!.strokeWidth = 5f
        paint!!.style = Paint.Style.FILL_AND_STROKE

        path = Path()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width
        val height = height

        paint?.color = Color.BLUE
        canvas.drawRect(0F, 0F, (width).toFloat(),
            (height * 0.5).toFloat(), paint!!)

        paint?.color = Color.GREEN
        canvas.drawRect(0F, (height * 0.5).toFloat(),
            (width).toFloat(), (height).toFloat(), paint!!)


        // Нарисуем квадрат в качестве основного здания
        val top = height / 3
        val bottom = 5 * top / 3
        val left = width / 4
        val right = 3 * width / 4

        paint?.color = Color.rgb(232, 156, 29)
        canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint!!)

        // Нарисуем трубу
        val tubeLeft = left + 3 * (right - left) / 4 - right / 20
        val tubeTop = top - (bottom - top) / 3
        val tubeRight = left + 3 * (right - left) / 4 + right / 20
        val tubeBottom = top

        paint?.color = Color.RED
        canvas.drawRect(tubeLeft.toFloat(), tubeTop.toFloat(),
            tubeRight.toFloat(), tubeBottom.toFloat(), paint!!)

        // Нарисуем треугольник в качестве крыши
        path!!.reset()
        path!!.moveTo(left.toFloat(), top.toFloat())
        path!!.lineTo(((left + right) / 2).toFloat(), (tubeTop).toFloat())
        path!!.lineTo(right.toFloat(), top.toFloat())
        path!!.close()

        paint?.color = Color.rgb(154, 79, 10)
        canvas.drawPath(path!!, paint!!)
    }
}
