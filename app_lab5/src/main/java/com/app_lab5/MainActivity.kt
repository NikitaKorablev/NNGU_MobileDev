package com.app_lab5

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app_lab5.databinding.ActivityMainBinding
import com.app_lab5.task1.MainActivity as Task1
import com.app_lab5.task2.MainActivity as Task2
import com.app_lab5.task4.MainActivity as Task4
import com.app_lab5.task6.MainActivity as Task6
import com.app_lab5.task7.MainActivity as Task7

class MainActivity : AppCompatActivity() {
    private val LOGIN_SETTINGS = "Settings"
    private val LOGIN_CHECK = "LoginCheckBox"

    private val LOGIN_email = "LoginCheckBox"
    private val LOGIN_password = "LoginCheckBox"

    private lateinit var binding: ActivityMainBinding
    private lateinit var settings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settings = getSharedPreferences(LOGIN_SETTINGS, MODE_PRIVATE)

        binding.button1.setOnClickListener(this::onTask1ButtonClicked)
        binding.button2.setOnClickListener(this::onTask2ButtonClicked)
        binding.button4.setOnClickListener(this::onTask4ButtonClicked)
        binding.button6.setOnClickListener(this::onTask6ButtonClicked)
        binding.button7.setOnClickListener(this::onTask7ButtonClicked)

        binding.logout.setOnClickListener(this::onLogoutButtonPressed)
    }

    private fun onTask1ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task1::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    private fun onTask2ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task2::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    private fun onTask4ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task4::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    private fun onTask6ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task6::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    private fun onTask7ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task7::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    private fun onLogoutButtonPressed(view: View?) {
        saveState()
        startLoginActivity()
    }

    private fun startLoginActivity() {
        val intent = Intent(baseContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveState() {
        settings.edit()
            .putString(LOGIN_email, "")
            .putString(LOGIN_password, "")
            .putBoolean(LOGIN_CHECK, false)
            .apply()
    }
}