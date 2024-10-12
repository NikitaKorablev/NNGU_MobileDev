package com.lab4.task7

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lab4.R
import com.lab4.databinding.ActivityMainTask7Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask7Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.loadUrl("https://github.com/NikitaKorablev")
        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}