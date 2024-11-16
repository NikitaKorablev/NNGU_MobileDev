package com.app_lab6.task7

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app_lab6.databinding.ActivityMainTask7Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask7Binding
    private lateinit var pageStack: PageStack
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val items = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPagerAdapter = ViewPagerAdapter(items)
        binding.viewPager.adapter = viewPagerAdapter
        binding.mainMenu.setOnClickListener(this::onMainMenuButtonClicked)

        pageStack = PageStack()

        binding.addButton.setOnClickListener(this::onAddButtonPressed)
        binding.delButton.setOnClickListener(this::onDelButtonPressed)
    }

    private fun onAddButtonPressed(view: View?) {
        items.add(Item("Page ${items.size+1}"))
        pageStack.addPage("Page ${items.size}")

        viewPagerAdapter.notifyItemInserted(items.size - 1)
        updatePageStackState()
    }

    private fun onDelButtonPressed(view: View?) {
        if (items.size == 0) return

        pageStack.removePage("Page ${items.size}")
        items.removeAt(items.size-1)

        viewPagerAdapter.notifyItemRemoved(items.size)
        updatePageStackState()
    }

    @SuppressLint("SetTextI18n")
    private fun updatePageStackState() {
        binding.addedCountText.text = "Added Pages: ${pageStack.addedPagesCount.intValue}"
        binding.removedCountText.text = "Removed Pages: ${pageStack.removedPagesCount.intValue}"
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDestroy() {
        super.onDestroy()
        items.clear()
        pageStack.clear()
        binding.viewPager.adapter?.notifyDataSetChanged()
        updatePageStackState()
    }
}