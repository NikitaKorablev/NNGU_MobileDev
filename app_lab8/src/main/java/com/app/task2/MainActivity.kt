package com.app.task2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.databinding.ActivityMainTask2Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}