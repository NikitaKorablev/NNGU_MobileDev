package com.app_lab5.task6

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app_lab5.databinding.ActivityMainTask6Binding
import com.app_lab5.task6.data.Note
import com.app_lab5.task6.viewConstrucor.ListViewAdapter
import com.app_lab5.task6.viewConstrucor.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainTask6Binding
    private lateinit var adapter: ListViewAdapter
    private val noteViewModel: NoteViewModel by viewModels()

    private val list = ArrayList<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTask6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListViewAdapter(list, this) { note ->
            noteViewModel.delete(note)
        }
        binding.listView.adapter = adapter
        binding.addNoteButton.setOnClickListener(this::onAddNoteButtonClicked)
        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            onListItemClicked(position)
        }

        noteViewModel.allNotes.observe(this) { notes ->
            notes?.let { adapter.items = it }
            adapter.notifyDataSetChanged()
        }

        binding.mainMenuButton.setOnClickListener(this::onMainMenuButtonClicked)
    }

    private fun onAddNoteButtonClicked(view: View?) {
        val text = binding.noteInput.text.toString()
        if (text.isNotEmpty()) {
            val note = Note(text = text)
            noteViewModel.insert(note)
            binding.noteInput.text.clear()
        }
    }

    private fun onListItemClicked(position: Int) {
        list.removeAt(position)
        adapter.notifyDataSetChanged()
    }

    private fun onMainMenuButtonClicked(view: View?) {
        finish()
    }
}