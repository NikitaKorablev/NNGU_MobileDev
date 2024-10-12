package com.lab4

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lab4.databinding.ActivityMainBinding
import com.lab4.task1.MainActivity as ActivityTask1
import com.lab4.task2.MainActivity as ActivityTask2
import com.lab4.task3.MainActivity as ActivityTask3
import com.lab4.task4.MainActivity as ActivityTask4
import com.lab4.task5.MainActivity as ActivityTask5
import com.lab4.task6.MainActivity as ActivityTask6
import com.lab4.task7.MainActivity as ActivityTask7

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
        binding.button5.setOnClickListener(this::onTask5ButtonClicked)
        binding.button6.setOnClickListener(this::onTask6ButtonClicked)
        binding.button7.setOnClickListener(this::onTask7ButtonClicked)
    }

    private fun onTask1ButtonClicked(view: View?) {
        val intent = Intent(baseContext, ActivityTask1::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }
    private fun onTask2ButtonClicked(view: View?) {
        val intent = Intent(baseContext, ActivityTask2::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }
    private fun onTask3ButtonClicked(view: View?) {
        val intent = Intent(baseContext, ActivityTask3::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }
    private fun onTask4ButtonClicked(view: View?) {
        val intent = Intent(baseContext, ActivityTask4::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }
    private fun onTask5ButtonClicked(view: View?) {
        val intent = Intent(baseContext, ActivityTask5::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }
    private fun onTask6ButtonClicked(view: View?) {
        val intent = Intent(baseContext, ActivityTask6::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }
    private fun onTask7ButtonClicked(view: View?) {
        val intent = Intent(baseContext, ActivityTask7::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }
}