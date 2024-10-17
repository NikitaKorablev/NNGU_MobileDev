package com.app_lab5.task2

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.app_lab5.databinding.ActivityMainTask2Binding
import com.app_lab5.task1.Item
import com.app_lab5.task1.ListViewAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask2Binding
    private lateinit var adapter: ListViewAdapter

    private val list = ArrayList<Item>()
    private var itemsCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initList()
        adapter = ListViewAdapter(list, this)
        binding.listView.adapter = adapter
        binding.addItemButton.setOnClickListener(this::onAddItemButtonClicked)
        binding.listView.onItemClickListener = AdapterView.OnItemClickListener {_, _, position, _ ->
            onListItemClicked(position)
        }

        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun initList() {
        list.add(Item("Элемент 1", textColor=Color.BLACK, bgColor=Color.WHITE))
        list.add(Item("Элемент 2", textColor=Color.BLACK, bgColor=Color.WHITE))
        list.add(Item("Элемент 3", textColor=Color.BLACK, bgColor=Color.WHITE))

        itemsCount = 3
    }

    private fun onAddItemButtonClicked(view: View?) {
        itemsCount += 1
        list.add(Item("Элемент $itemsCount", textColor=Color.BLACK, bgColor=Color.WHITE))

        adapter.notifyDataSetChanged()
    }

    private fun onListItemClicked(position: Int) {
        list.removeAt(position)
        adapter.notifyDataSetChanged()
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}