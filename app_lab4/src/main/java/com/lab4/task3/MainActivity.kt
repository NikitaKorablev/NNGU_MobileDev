package com.lab4.task3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.lab4.R
import com.lab4.databinding.ActivityMainTask3Binding

class MainActivity : AppCompatActivity(), DialogFr.OnDataSendListener {
    private lateinit var binding: ActivityMainTask3Binding

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showAlertButton.setOnClickListener(this::onShowButtonClicked)
        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    override fun showData(inputText: String) {
        binding.textView.text = inputText
    }

    private fun onShowButtonClicked(view: View?) {
        DialogFr().show(supportFragmentManager, "DialogFr")
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}