package com.app_lab6.task5

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.app_lab6.R

class CustomButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        orientation = VERTICAL
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButtonView)
        val buttonColor = typedArray.getColor(R.styleable.CustomButtonView_buttonColor, Color.GRAY)
        typedArray.recycle()

        post {
            // Получаем дочерние TextView элементы
            for (i in 0 until childCount) {
                val view = getChildAt(i)
                if (view is TextView) {
                    createButton(view.text.toString(), buttonColor)
                }
            }
        }
    }

    private fun createButton(text: String, buttonColor: Int) {
        val button = Button(context).apply {
            this.text = text
            this.setBackgroundColor(buttonColor)

            // Установка ширины и высоты кнопки
            val layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT, // Ширина кнопки
                LayoutParams.WRAP_CONTENT  // Высота кнопки
            )
            this.layoutParams = layoutParams
        }

        addView(button)
    }
}