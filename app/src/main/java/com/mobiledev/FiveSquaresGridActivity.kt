package com.mobiledev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FiveSquaresGridActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.five_squares_grid)

        val buttonNext = findViewById<Button>(R.id.button_next)
        val buttonPrev = findViewById<Button>(R.id.button_prev)

        buttonNext.setOnClickListener {
            val intent = Intent(this, BlackSquaresActivity::class.java)
            startActivity(intent)
        }
        buttonPrev.setOnClickListener {
            val intent = Intent(this, FiveSquaresActivity::class.java)
            startActivity(intent)
        }
    }
}