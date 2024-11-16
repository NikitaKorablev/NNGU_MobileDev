package com.app_lab6.task5

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app_lab6.R
import com.app_lab6.databinding.ActivityMainTask5Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainMenu.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}