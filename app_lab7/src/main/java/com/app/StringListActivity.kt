package com.app

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.databinding.ActivityStringListBinding
import kotlinx.coroutines.launch

class StringListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStringListBinding

    init {
        System.loadLibrary("app")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStringListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createStringList()
        val repository = ApiRepository()

        val addButton = binding.addButton
        val editText = binding.editText
        val removeButton = binding.removeButton

        addButton.setOnClickListener {
            val inputText = editText.text.toString()
            if (inputText.isNotEmpty()) {
                addString(inputText.lowercase())
                editText.text.clear()
                updateDisplay()
            } else {
                Toast.makeText(this, "Введите строку", Toast.LENGTH_SHORT).show()
            }
        }

        removeButton.setOnClickListener {
            removeLastString()
            updateDisplay()
        }

        binding.sendButton.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val word = repository.checkWordExistence(editText.text.toString())
                    Toast.makeText(
                        this@StringListActivity, "Word validate = ${word.isNotEmpty()}", Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@StringListActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.mainMenuButton.setOnClickListener(this::onExitButtonClicked)
    }

    private fun updateDisplay() {
        val formattedStrings = getFormattedStrings()
        val capitalizedString = formattedStrings.replaceFirstChar { it.uppercase() }
        binding.formattedTextView.text = capitalizedString
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyStringList()
    }

    private fun onExitButtonClicked(view: View?) {
        finish()
    }

    private external fun createStringList()
    private external fun addString(str: String)
    private external fun removeLastString()
    private external fun getFormattedStrings(): String
    private external fun destroyStringList()
}
