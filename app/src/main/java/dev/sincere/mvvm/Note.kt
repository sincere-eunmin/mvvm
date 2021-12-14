package dev.sincere.mvvm

import java.time.LocalDate

data class Note(
    val title: String,
    val content: String,
    val date: LocalDate
)