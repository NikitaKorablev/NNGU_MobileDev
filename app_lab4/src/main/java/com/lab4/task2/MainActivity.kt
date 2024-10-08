package com.lab4.task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lab4.databinding.ActivityMainTask2Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, MainFragment())
                .commit()
        }
    }
}