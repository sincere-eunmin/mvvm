package dev.sincere.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _notes = MutableStateFlow(listOf<Note>())
    val notes = _notes.asStateFlow()

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            Notes.notes.collect { data ->
                _notes.emit(data)
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            val a = async {Notes.deleteNote(note) }
            a.await()
            getNotes()
        }


    }
}