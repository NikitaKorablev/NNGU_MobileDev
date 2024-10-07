package com.lab3

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.lab3.databinding.FirstActivityBinding

class FirstActivity : AppCompatActivity() {
    private var button1IsPressed = false
    private var clickCount = 0

    private lateinit var binding: FirstActivityBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button2 = binding.button2
        val button2Status = binding.buttonStatus

        binding.button1.setOnClickListener(this::button1IsPressed)
        binding.button2.setOnTouchListener { _, event ->
            when (event.action) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    button2.setBackgroundColor(Color.CYAN)
                    button2.setTextColor(Color.BLACK)
                    button2Status.text = "Нажата"
                }
                android.view.MotionEvent.ACTION_UP -> {
                    button2.setBackgroundColor(Color.rgb(103, 58, 183))
                    button2.setTextColor(Color.WHITE)
                    button2Status.text = "Отпущена"
                }
            }
            true
        }
        binding.clickCounter.setOnClickListener(this::clickCounterButtonOnClick)
        binding.chooseDateButton.setOnClickListener(this::chooseDateData)
        binding.chooseTimeData.setOnClickListener(this::chooseTimeData)
        binding.switch1.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked)
                binding.switch1.text = "Включен"
            else
                binding.switch1.text = "Выключен"
        }

        binding.imageView.setImageResource(R.drawable.white)

        val spinner: Spinner = binding.planetsSpinner
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedPlanet = parent.getItemAtPosition(position) as String
                when (selectedPlanet) {
                    "NOTHING" -> binding.imageView.setImageResource(R.drawable.white)
                    "Mercury" -> binding.imageView.setImageResource(R.drawable.mercury)
                    "Venus" -> binding.imageView.setImageResource(R.drawable.venus)
                    "Earth" -> binding.imageView.setImageResource(R.drawable.earth)
                    "Mars" -> binding.imageView.setImageResource(R.drawable.mars)
                    "Jupiter" -> binding.imageView.setImageResource(R.drawable.jupiter)
                    "Saturn" -> binding.imageView.setImageResource(R.drawable.saturn)
                    "Uranus" -> binding.imageView.setImageResource(R.drawable.uranus)
                    "Neptune" -> binding.imageView.setImageResource(R.drawable.neptune)
                }



                if (selectedPlanet != "NOTHING") Toast.makeText(parent.context,
                    "Selected planet: $selectedPlanet", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val seekBar: SeekBar = binding.seekBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textView7.text = seekBar!!.progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {     }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun button1IsPressed(view: View?) {
        val button = binding.button1

        if (!button1IsPressed) {
            button.setBackgroundColor(Color.CYAN)
            button.text = "Pressed"
            button.setTextColor(Color.BLACK)
            button1IsPressed = true
        } else {
            button.setBackgroundColor(Color.rgb(103, 58, 183))
            button.text = "Unpressed"
            button.setTextColor(Color.WHITE)
            button1IsPressed = false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun clickCounterButtonOnClick(view: View?) {
        clickCount++
        binding.clickCounter.text = "Счетчик $clickCount"
    }

    private fun chooseDateData(view: View?) {
        DatePickerFragment().show(supportFragmentManager, "DatePicker")
    }

    private fun chooseTimeData(view: View?) {
        TimePickerFragment().show(supportFragmentManager, "TimePicker")
    }

}