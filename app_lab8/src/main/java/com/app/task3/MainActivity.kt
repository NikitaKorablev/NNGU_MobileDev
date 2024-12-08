package com.app.task3

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.databinding.ActivityMainTask3Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}