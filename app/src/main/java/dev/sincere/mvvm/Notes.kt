package dev.sincere.mvvm

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

object Notes {
    private val _notes = mutableListOf<Note>()
    val notes: Flow<List<Note>>
        get() = flow {
        emit(_notes)
    }.map {
        it.filter { it.content.contains("Content") }
    }

    init {
        with(_notes) {
            add(Note("first", "First Content", LocalDate.of(2021, 10, 26)))
            add(Note("second", "second Content", LocalDate.of(2021, 10, 27)))
            add(Note("test3", "test3 Content", LocalDate.of(2021, 10, 27)))
            add(Note("aaaa", "aaaa Content", LocalDate.of(2021, 10, 27)))
            add(Note("bbbb", "bbbb Content", LocalDate.of(2021, 10, 27)))
            add(Note("gun", "gun Content", LocalDate.of(2021, 10, 27)))
        }
    }

    fun deleteNote(note: Note) {
        if (_notes.contains(note)) {
            _notes.remove(note)
        }
    }
}