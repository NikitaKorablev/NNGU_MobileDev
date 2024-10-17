package com.app_lab5.task6.viewConstrucor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app_lab5.task6.data.NoteDatabase
import com.app_lab5.task6.data.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val noteDao = NoteDatabase.getDatabase(application).noteDao()
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    fun insert(note: Note) {
        viewModelScope.launch {
            noteDao.insert(note)
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            noteDao.delete(note)
        }
    }
}