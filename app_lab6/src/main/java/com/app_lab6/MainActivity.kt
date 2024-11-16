package com.app_lab6

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app_lab6.databinding.ActivityMainBinding
import com.app_lab6.task1.MainActivity as Task1
import com.app_lab6.task3.MainActivity as Task3
import com.app_lab6.task4.MainActivity as Task4
import com.app_lab6.task5.MainActivity as Task5
import com.app_lab6.task6.MainActivity as Task6
import com.app_lab6.task7.MainActivity as Task7

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener(this::onTask1ButtonClicked)
        binding.button3.setOnClickListener(this::onTask3ButtonClicked)
        binding.button4.setOnClickListener(this::onTask4ButtonClicked)
        binding.button5.setOnClickListener(this::onTask5ButtonClicked)
        binding.button6.setOnClickListener(this::onTask6ButtonClicked)
        binding.button7.setOnClickListener(this::onTask7ButtonClicked)
    }

    private fun onTask1ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task1::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    private fun onTask3ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task3::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    private fun onTask4ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task4::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    private fun onTask5ButtonClicked(view: View?) {
        val intent = Intent(baseContext, Task5::class.java)
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
}