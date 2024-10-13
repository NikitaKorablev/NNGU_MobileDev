package com.lab4.task10

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lab4.R
import com.lab4.databinding.ActivityMainTask10Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask10Binding
    private val items = ArrayList<Item>()
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask10Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initItemsList()
        adapter = ViewPagerAdapter(items)
        binding.recyclerView.adapter = adapter

        registerForContextMenu(binding.recyclerView)
        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun initItemsList() {
        items.add(Item(1, "Some text"))
        items.add(Item(2, "Abra Cadabra"))
        items.add(Item(3, "Hello World"))
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.top_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = adapter.pos

        Toast.makeText(this, "${items[position].text}: ${item.title}", Toast.LENGTH_LONG).show()
        return true
    }
}