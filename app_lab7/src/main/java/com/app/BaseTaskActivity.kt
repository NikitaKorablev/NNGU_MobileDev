package com.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.databinding.BaseTaskActivityMainBinding

class BaseTaskActivity : AppCompatActivity() {
    init {
        System.loadLibrary("app")
    }

    private lateinit var binding: BaseTaskActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BaseTaskActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        createCounter()
        createCalculator()

        binding.add.setOnClickListener {
            setVals(binding.val1.text.toString().toDouble(), binding.val2.text.toString().toDouble())
            add()
            updateCalculateValue()
        }

        binding.mul.setOnClickListener {
            setVals(binding.val1.text.toString().toDouble(), binding.val2.text.toString().toDouble())
            mul()
            updateCalculateValue()
        }

        binding.div.setOnClickListener {
            setVals(binding.val1.text.toString().toDouble(), binding.val2.text.toString().toDouble())
            div()
            updateCalculateValue()
        }
        binding.sub.setOnClickListener {
            setVals(binding.val1.text.toString().toDouble(), binding.val2.text.toString().toDouble())
            sub()
            updateCalculateValue()
        }

//        binding.resetButton.setOnClickListener {
//            resetCounter()
//            updateCounterValue()
//        }

        binding.mainMenuButton.setOnClickListener(this::onExitButtonClicked)

        updateCounterValue()
    }

    override fun onDestroy() {
        super.onDestroy()
        deleteCounter()
    }

    private fun updateCounterValue() {
        binding.valueTextView.text = getCounterValue().toString()
    }
    private fun updateCalculateValue() {
        binding.valueTextView.text = getV1().toString()
    }

    private fun onExitButtonClicked(view: View?) {
        finish()
    }

    private external fun createCounter()
    private external fun incrementCounter()
    private external fun resetCounter()
    private external fun getCounterValue(): Int
    private external fun deleteCounter()

    private external fun createCalculator()
    private external fun add()
    private external fun mul()
    private external fun sub()
    private external fun div()
    private external fun getV1(): Double
    private external fun getV2(): Double
    private external fun setVals(v1: Double, v2: Double)
}