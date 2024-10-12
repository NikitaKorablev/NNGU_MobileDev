package com.lab4.task6

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.lab4.R

class MainActivity : AppCompatActivity() {
    private val items = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_task6)

        initItemsList()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        val adapter = ItemAdapter(this, items)

        recyclerView.adapter = adapter
        findViewById<Button>(R.id.mainMenuButton).setOnClickListener(this::onMainMenuButtonClicked)
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