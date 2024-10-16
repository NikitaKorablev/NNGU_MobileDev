package com.app_lab5.task1

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app_lab5.R
import com.app_lab5.databinding.ActivityMainTask1Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask1Binding
    private val list = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initList()
        val adapter = ListViewAdapter(list, this)
        binding.listView.adapter = adapter
        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun initList() {
        list.add(Item("Белый", textColor=Color.BLACK, bgColor=Color.WHITE))
        list.add(Item("Черный", textColor=Color.WHITE, bgColor=Color.BLACK))
        list.add(Item("Синий", textColor=Color.MAGENTA, bgColor=Color.BLUE))
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}