package com.app_lab6.task1

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app_lab6.R
import com.app_lab6.databinding.ActivityMainTask1Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask1Binding
    private var job: Job? = null
    private var trafficIsStarted = false
    private var manAnimator: ObjectAnimator? = null
    private var manIsLeft = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainMenu.setOnClickListener(this::onMainMenuButtonClicked)

        startTrafficLights()
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
                startManAnimation()
                delay(3000)
                stopManAnimation()
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

    private fun startManAnimation() {
        if (manIsLeft)
            manAnimator = ObjectAnimator.ofFloat(binding.man, "translationX", 0f, binding.root.width.toFloat() - binding.man.width)
        else
            manAnimator = ObjectAnimator.ofFloat(binding.man, "translationX", binding.root.width.toFloat() - binding.man.width, 0f)

        manAnimator?.duration = 3000
        manAnimator?.start()
    }

    private fun stopManAnimation() {
        if (manIsLeft) {
            binding.man.scaleX = -1f
            manIsLeft = false
        } else {
            binding.man.scaleX = 1f
            manIsLeft = true
        }

        manAnimator?.cancel()
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        trafficIsStarted = false
        job?.cancel()
        stopManAnimation()
    }
}