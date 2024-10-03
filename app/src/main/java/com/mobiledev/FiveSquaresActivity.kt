package com.mobiledev

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FiveSquaresActivity: AppCompatActivity() {
    private lateinit var text1: TextView
    private lateinit var text2: TextView
    private lateinit var text3: TextView
    private lateinit var text4: TextView
    private lateinit var text5: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.five_squares)

        val buttonNext = findViewById<Button>(R.id.button_next)
        val buttonPrev = findViewById<Button>(R.id.button_prev)

        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)
        text3 = findViewById(R.id.text3)
        text4 = findViewById(R.id.text4)
        text5 = findViewById(R.id.text5)

        buttonNext.setOnClickListener {
            val intent = Intent(this, FiveSquaresGridActivity::class.java)
            startActivity(intent)
        }
        buttonPrev.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        animateRectangle()
    }

    private fun animateRectangle() {
        val moveAnimator1 = ObjectAnimator.ofFloat(text1, "rotation", 360f)
        moveAnimator1.repeatCount = ValueAnimator.INFINITE
        val moveAnimator2 = ObjectAnimator.ofFloat(text2, "rotation", 360f)
        moveAnimator2.repeatCount = ValueAnimator.INFINITE
        val moveAnimator3 = ObjectAnimator.ofFloat(text3, "rotation", 360f)
        moveAnimator3.repeatCount = ValueAnimator.INFINITE
        val moveAnimator4 = ObjectAnimator.ofFloat(text4, "rotation", 360f)
        moveAnimator4.repeatCount = ValueAnimator.INFINITE
        val moveAnimator5 = ObjectAnimator.ofFloat(text5, "rotation", 360f)
        moveAnimator5.repeatCount = ValueAnimator.INFINITE


        val animatorSet = AnimatorSet()
        animatorSet.playTogether(moveAnimator1, moveAnimator2, moveAnimator3, moveAnimator4, moveAnimator5)
        animatorSet.duration = 2000

        animatorSet.start()
    }
}