package com.mobiledev

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BlackSquaresAnimationActivity: AppCompatActivity() {
    private lateinit var rectBlack: ImageView
    private lateinit var textSquare: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.black_square_animation)

        rectBlack = findViewById(R.id.rect_black)
        val buttonNext = findViewById<Button>(R.id.button_next)
        val buttonPrev = findViewById<Button>(R.id.button_prev)
        textSquare = findViewById<TextView>(R.id.squareLabel)
        buttonNext.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }
        buttonPrev.setOnClickListener {
            val intent = Intent(this, BlackSquaresActivity::class.java)
            startActivity(intent)
        }

//        rectBlack.setOnClickListener(this::anim1)
        anim1()
    }

    private fun anim1() {
        animateRectangle(500f, 2f) {
            anim2()
        }
    }

    private fun anim2() {
        animateRectangle(1 / 500f, 1f) {
            anim1()
        }
    }

    private fun animateRectangle(translationY: Float, scale: Float, onAnimationEnd: () -> Unit) {
        val moveAnimator = ObjectAnimator.ofFloat(rectBlack, "translationY", translationY)
        val scaleXAnimator = ObjectAnimator.ofFloat(rectBlack, "scaleX", scale)
        val scaleYAnimator = ObjectAnimator.ofFloat(rectBlack, "scaleY", scale)

        val moveAnimatorT = ObjectAnimator.ofFloat(textSquare, "translationY", translationY)
        val scaleXAnimatorT = ObjectAnimator.ofFloat(textSquare, "scaleX", scale)
        val scaleYAnimatorT = ObjectAnimator.ofFloat(textSquare, "scaleY", scale)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(moveAnimator, scaleXAnimator, scaleYAnimator,moveAnimatorT,scaleXAnimatorT,scaleYAnimatorT)
        animatorSet.duration = 2000

        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                onAnimationEnd() // Перезапускаем анимацию после завершения
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        animatorSet.start()
    }

}