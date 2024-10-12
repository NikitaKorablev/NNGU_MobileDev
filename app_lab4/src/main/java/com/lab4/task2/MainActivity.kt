package com.lab4.task2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.lab4.databinding.ActivityMainTask2Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, MainFragment())
                .commit()
        }

        binding.mainMenuButton.setOnClickListener(this::onMainManuButtonClicked)
    }

    private fun onMainManuButtonClicked(view: View?) {
        for (fragment in supportFragmentManager.fragments)
            supportFragmentManager.beginTransaction().remove(fragment).commit()

        finish()
    }
}