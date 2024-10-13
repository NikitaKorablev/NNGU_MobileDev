package com.lab4.task11

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.lab4.R
import org.w3c.dom.Text

class FloatingWindowService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var floatingView: View
    private var counter = 0

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        // Inflate your floating layout
        floatingView = LayoutInflater.from(this).inflate(R.layout.activity_main_task11, null)

        // Set the layout parameters for the floating window
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            } else {
                WindowManager.LayoutParams.TYPE_PHONE
            },
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        // Set the position of the floating window
        params.gravity = Gravity.TOP or Gravity.START
        params.x = 0
        params.y = 100

        // Add the view to the window
        windowManager.addView(floatingView, params)

        // Set up touch listeners for dragging the window
        floatingView.setOnTouchListener(object : View.OnTouchListener {
            var initialX = 0
            var initialY = 0
            var initialTouchX = 0f
            var initialTouchY = 0f

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        initialX = params.x
                        initialY = params.y
                        initialTouchX = event.rawX
                        initialTouchY = event.rawY
                        return true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        params.x = initialX + (event.rawX - initialTouchX).toInt()
                        params.y = initialY + (event.rawY - initialTouchY).toInt()
                        windowManager.updateViewLayout(floatingView, params)
                        return true
                    }
                }
                return false
            }
        })

        // Get references to buttons and text view
        val textViewCounter: TextView = floatingView.findViewById(R.id.value)
        val buttonAdd: Button = floatingView.findViewById(R.id.incrementButton)
        val buttonReset: Button = floatingView.findViewById(R.id.resetButton)
        val buttonClose: TextView = floatingView.findViewById(R.id.close)

        // Update the counter display
        updateCounterDisplay(textViewCounter)

        // Set up button click listeners
        buttonAdd.setOnClickListener {
            counter++
            updateCounterDisplay(textViewCounter)
        }

        buttonReset.setOnClickListener {
            counter = 0
            updateCounterDisplay(textViewCounter)
        }
        buttonClose.setOnClickListener {
            stopSelf() // Закрывает сервис и удаляет плавающее окно
        }
    }

    private fun updateCounterDisplay(textView: TextView) {
        textView.text = "Счет: $counter"
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::floatingView.isInitialized) windowManager.removeView(floatingView)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
