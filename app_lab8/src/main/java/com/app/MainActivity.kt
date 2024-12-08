package com.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.databinding.ActivityMainBinding
import com.app.task1.MainActivity as Task1
import com.app.task2.MainActivity as Task2
import com.app.task3.MainActivity as Task3
import com.app.task4.MainActivity as Task4

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener(this::onTask1ButtonClicked)
        binding.button2.setOnClickListener(this::onTask2ButtonClicked)
        binding.button3.setOnClickListener(this::onTask3ButtonClicked)
        binding.button4.setOnClickListener(this::onTask4ButtonClicked)
    }

    private fun onTask1ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task1::class.java)
        startActivity(intent)
    }
    private fun onTask2ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task2::class.java)
        startActivity(intent)
    }
    private fun onTask3ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task3::class.java)
        startActivity(intent)
    }
    private fun onTask4ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task4::class.java)
        startActivity(intent)
    }
}