package com.lab4.task8

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lab4.databinding.ActivityMainTask8Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask8Binding
    private val items = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initItemsList()
        binding.viewPager.adapter = ViewPagerAdapter(items)
        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun initItemsList() {
        items.add(Item("Сделать дело\nГулять смело", "17.02.2017"))
        items.add(Item("Прочитать книгу", "16.02.2017"))
        items.add(Item("Сходить на учебу", "15.02.2017"))
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}