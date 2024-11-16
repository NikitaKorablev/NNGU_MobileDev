package com.app_lab6.task4

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app_lab6.R
import com.app_lab6.databinding.ActivityMainTask4Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask4Binding
    private var job: Job? = null
    private var trafficIsStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainMenu.setOnClickListener(this::onMainMenuButtonClicked)


        setupDraggableCircle(binding.circleRed)
        setupDraggableCircle(binding.circleGreen)
        setupDraggableCircle(binding.circleYellow)

//        startTrafficLights()
    }

    private fun startTrafficLights() {
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

    private fun switchLights(light: String) {
        binding.circleRed.background = ContextCompat.getDrawable(this, R.drawable.circle_black)
        binding.circleYellow.background = ContextCompat.getDrawable(this, R.drawable.circle_black)
        binding.circleGreen.background = ContextCompat.getDrawable(this, R.drawable.circle_black)

        when (light) {
            "red" -> binding.circleRed.background = ContextCompat.getDrawable(this, R.drawable.circle_red)
            "yellow" -> binding.circleYellow.background = ContextCompat.getDrawable(this, R.drawable.circle_yellow)
            "green" -> binding.circleGreen.background = ContextCompat.getDrawable(this, R.drawable.circle_green)
        }
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }

    private fun stopTrafficLights() {
        if (!trafficIsStarted) return

        trafficIsStarted = false
        job?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTrafficLights()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupDraggableCircle(circle: View) {
        circle.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    v.x = event.rawX - v.width / 2
                    v.y = event.rawY - v.height / 2
                    if (checkCollision() && checkYPosition()) startTrafficLights()
                    else stopTrafficLights()
                }
            }
            true
        }
    }

    private fun checkCollision(): Boolean {
        val a = checkCollisionWithSquare(binding.circleRed)
        val b = checkCollisionWithSquare(binding.circleYellow)
        val c = checkCollisionWithSquare(binding.circleGreen)

        return a && b && c
    }


    private fun checkCollisionWithSquare(circle: View): Boolean {
        val squareRect = Rect().apply {
            binding.graySquare.getGlobalVisibleRect(this)
        }

        val circleRect = Rect().apply {
            circle.getGlobalVisibleRect(this)
        }

        // Проверяем полное нахождение круга внутри квадрата
        return squareRect.contains(circleRect)
    }


//    private fun checkCollisionWithSquare(circle: View): Boolean {
//        val square = binding.graySquare
//        val hor: Boolean = circle.left > square.left && circle.right < square.right
//        val ver: Boolean = circle.top > square.top && circle.bottom < square.bottom
//
//        return hor && ver
//
////        val circleRect = Rect(
////            circle.left,
////            circle.top,
////            circle.right,
////            circle.bottom
////        )
////        val squareRect = Rect(
////            binding.graySquare.left,
////            binding.graySquare.top,
////            binding.graySquare.right,
////            binding.graySquare.bottom
////        )
////
////        val intersect = Rect.intersects(circleRect, squareRect)
////        return intersect
//    }

    private fun checkYPosition(): Boolean {
        return binding.circleRed.y < binding.circleYellow.y && binding.circleYellow.y < binding.circleGreen.y
    }
}