package com.app.task1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.databinding.ActivityMainTask1Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}