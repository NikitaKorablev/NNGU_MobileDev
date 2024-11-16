package com.app_lab6.task6

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app_lab6.databinding.ActivityMainTask6Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask6Binding
    private lateinit var stopWatch: StopWatch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        stopWatch = findViewById(binding.stopWatch.id)
        stopWatch.setTextViews(binding.hours, binding.min, binding.sec)

        binding.startButton.setOnClickListener(this::onStartButtonClicked)
        binding.mainMenu.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun onStartButtonClicked(view: View?) {
        stopWatch.start()
        binding.startButton.text = "Stop"
        binding.startButton.setOnClickListener(this::onStopButtonClicked)
    }

    private fun onStopButtonClicked(view: View?) {
        stopWatch.stop()
        binding.startButton.text = "Start"
        binding.startButton.setOnClickListener(this::onStartButtonClicked)
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopWatch.stop()
    }
}