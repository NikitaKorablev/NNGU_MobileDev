package com.lab4.task11

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lab4.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Start the floating window service
        val intent = Intent(this, FloatingWindowService::class.java)
        startService(intent)

        // Close the main activity if you only want the floating window
        finish()
    }
}

