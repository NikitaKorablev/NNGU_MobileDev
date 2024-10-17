package com.app_lab5.task7

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app_lab5.databinding.ActivityMainTask7Binding

class MainActivity : AppCompatActivity() {
    private val PREF_SETTINGS = "Settings"
    private val PREF_TEXT = "Text"
    private val PREF_CHECK = "CheckBox"

    private lateinit var binding: ActivityMainTask7Binding
    private lateinit var settings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        settings = getSharedPreferences(PREF_SETTINGS, MODE_PRIVATE)

        val text = settings.getString(PREF_TEXT, "")
        val isChecked = settings.getBoolean(PREF_CHECK, false)

        binding.editText.setText(text)
        binding.checkBox.isChecked = isChecked
        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    override fun onPause() {
        super.onPause()
        saveState()
    }

    private fun saveState() {
        val text = binding.editText.text.toString()
        val isChecked = binding.checkBox.isChecked

        settings.edit()
            .putString(PREF_TEXT, text)
            .putBoolean(PREF_CHECK, isChecked)
            .apply()
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}