package com.lab4.task4

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lab4.databinding.ActivityMainTask4Binding

class MainActivity : AppCompatActivity(), DatePickerFragment.DateListener {
    private lateinit var binding: ActivityMainTask4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chooseDateButton.setOnClickListener(this::onChooseDateButtonClicked)
        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun onChooseDateButtonClicked(view: View?) {
        DatePickerFragment().show(supportFragmentManager, "DatePickerFragment")
    }

    @SuppressLint("SetTextI18n")
    override fun dateUpdate(year: Int, month: Int, day: Int) {
        binding.date.text = "$year.$month.$day"
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}