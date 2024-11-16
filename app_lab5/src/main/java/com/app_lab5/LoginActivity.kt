package com.app_lab5

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app_lab5.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val LOGIN_SETTINGS = "Settings"
    private val LOGIN_CHECK = "LoginCheckBox"

    private val LOGIN_email = "LoginCheckBox"
    private val LOGIN_password = "LoginCheckBox"

    private lateinit var binding: ActivityLoginBinding
    private lateinit var settings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settings = getSharedPreferences(LOGIN_SETTINGS, MODE_PRIVATE)

        val isChecked = settings.getBoolean(LOGIN_CHECK, false)
        if (isChecked) startMainActivity()

        binding.login.setOnClickListener(this::onLoginButtonPressed)
    }

    private fun startMainActivity() {
        val intent = Intent(baseContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun onLoginButtonPressed(view: View?) {
        saveState()
        startMainActivity()
    }

    override fun onPause() {
        super.onPause()
        saveState()
    }

    private fun saveState() {
        val text = binding.email.text.toString()
        val password = binding.password.text.toString()
        val checkBox = binding.checkBox.isChecked

        settings.edit()
            .putString(LOGIN_email, text)
            .putString(LOGIN_password, password)
            .putBoolean(LOGIN_CHECK, checkBox)
            .apply()
    }
}