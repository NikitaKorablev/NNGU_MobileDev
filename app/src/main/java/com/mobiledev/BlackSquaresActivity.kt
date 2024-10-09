package com.mobiledev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BlackSquaresActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.black_squares)

        val buttonNext = findViewById<Button>(R.id.button_next)
        val buttonPrev = findViewById<Button>(R.id.button_prev)

        buttonNext.setOnClickListener {
            val intent = Intent(this, BlackSquaresAnimationActivity::class.java)
            startActivity(intent)
        }
        buttonPrev.setOnClickListener {
            val intent = Intent(this, FiveSquaresGridActivity::class.java)
            startActivity(intent)
        }
    }
}