package com.lab4.task5

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lab4.databinding.ActivityMainTask5Binding

class MainActivity : AppCompatActivity(), TimePickerFragment.TimeListener {
    private lateinit var binding: ActivityMainTask5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chooseTimeButton.setOnClickListener(this::onChooseDateButtonClicked)
        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun onChooseDateButtonClicked(view: View?) {
        TimePickerFragment().show(supportFragmentManager, "DatePickerFragment")
    }

    @SuppressLint("SetTextI18n")
    override fun timeUpdate(hourOfDay: Int, minute: Int) {
        binding.time.text = "$hourOfDay:$minute"
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}