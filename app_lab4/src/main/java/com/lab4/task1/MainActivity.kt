package com.lab4.task1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lab4.databinding.ActivityMainTask1Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val arguments = intent.extras
        if (arguments != null && arguments.containsKey("depth")) {
            binding.stackDepth.text = arguments.getInt("depth").toString()
            binding.mainMenuButton.visibility = View.GONE
        } else {
            binding.stackDepth.text = "0"
            binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
        }

        binding.prevButton.setOnClickListener(this::prevButtonOnClick)
        binding.nextButton.setOnClickListener(this::nextButtonOnClick)
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }

    private fun prevButtonOnClick(view: View?) {
        finish()
    }

    private fun nextButtonOnClick(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("depth", Integer.parseInt(binding.stackDepth.text.toString()) + 1)
        startActivity(intent)
    }
}